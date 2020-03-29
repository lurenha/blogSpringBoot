package com.peng.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.sys.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Insert("<script>" +
            "INSERT INTO sys_role_menu(role_id,menu_id) VALUES " +
            "<foreach collection='list' item='menuId' separator=','>" +
            " (#{roleId},#{menuId})" +
            "</foreach>" +
            "</script>")
    int addRoleMenuBatch(@Param("roleId") Long roleId, @Param("list") List<Long> list);

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteRoleMenuBatch(@Param("roleId") Long roleId);
}
