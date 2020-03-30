package com.peng.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单表 数据层
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Select("<script>" +
            " select menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, ifnull(perms,'') as perms, icon, create_time \n" +
            "from sys_menu where 1=1\n" +
            "<if test='menuName!=null and menuName!=\"\"'> " +
            "AND menu_name like concat('%', #{menuName}, '%')" +
            "</if>" +
            "<if test='visible!=null and visible!=\"\"'> " +
            "AND visible = #{visible}" +
            "</if>" +
            "</script>")
    List<SysMenu> selectMenuList(SysMenu menu);


    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Select("<script>" +
            "select distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, ifnull(m.perms,'') as perms, m.is_frame, m.menu_type, m.icon, m.order_num, m.create_time\n" +
            "from sys_menu m\n" +
            "left join sys_role_menu rm on m.menu_id = rm.menu_id\n" +
            "left join sys_user us on us.role_id = rm.role_id\n" +
            "where us.us_id = #{params.usId}\n" +
            "<if test='menuName!=null and menuName!=\"\"'> " +
            "AND menu_name like concat('%', #{menuName}, '%')" +
            "</if>" +
            "<if test='visible!=null and visible!=\"\"'> " +
            "AND visible = #{visible}" +
            "</if>" +
            "</script>")
    List<SysMenu> selectMenuListByUserId(SysMenu menu);


    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Select("select distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, ifnull(m.perms,'') as perms, m.is_frame, m.menu_type, m.icon, m.order_num, m.create_time\n" +
            "from sys_menu m where m.menu_type in ('M', 'C') and m.visible = 0\n" +
            "order by m.parent_id, m.order_num")
    List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Select("select distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, ifnull(m.perms,'') as perms, m.is_frame, m.menu_type, m.icon, m.order_num, m.create_time\n" +
            "from sys_menu m\n" +
            "left join sys_role_menu rm on m.menu_id = rm.menu_id\n" +
            "left join sys_role ro on rm.role_id = ro.role_id\n" +
            "left join sys_user u on ro.role_id = u.role_id\n" +
            "where u.us_id = #{userId} and m.menu_type in ('M', 'C') and m.visible = 0  AND ro.status = 0\n" +
            "order by m.parent_id, m.order_num")
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Select("select m.menu_id, m.parent_id\n" +
            "from sys_menu m\n" +
            "left join sys_role_menu rm on m.menu_id = rm.menu_id\n" +
            "where rm.role_id = #{roleId}\n" +
            "and m.menu_id not in (select m.parent_id from sys_menu m inner join sys_role_menu rm on m.menu_id = rm.menu_id and rm.role_id = #{roleId})\n" +
            "order by m.parent_id, m.order_num")
    List<Integer> selectMenuListByRoleId(Long roleId);


}
