package com.peng.shiro;



import com.peng.aspect.MyCache;
import com.peng.entity.User;
import com.peng.service.ICacheService;
import com.peng.service.IUserService;
import com.peng.util.RedisUtil;
import com.peng.util.TokenUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRealm extends AuthorizingRealm {

    @Lazy//Shiro会和AOP冲突导致AOP失效，延迟注入
    @Autowired
    private IUserService iUserService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如后台管理操作之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = TokenUtil.getUserId(principals.toString());
        //去数据库查找权限
        List<String> permissionList = iUserService.getPermissionList(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得usId，用于和数据库进行对比
        Long userId = TokenUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }

//        User user = userService.getById(userId);
//        if (user == null) {
//            throw new AuthenticationException("User didn't existed!");
//        }

        if (! TokenUtil.verify(token)) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
