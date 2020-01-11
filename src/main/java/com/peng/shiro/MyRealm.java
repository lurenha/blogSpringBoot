package com.peng.shiro;


import com.peng.domain.User;
import com.peng.service.Impl.UserService;
import com.peng.util.TokenUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRealm extends AuthorizingRealm {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
        Integer userid = TokenUtil.getUserid(principals.toString());
        User user = userService.findByid(userid);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(user.getPermissionlist());
        System.out.println(user.getPermissionlist());
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得userid，用于和数据库进行对比
        Integer userid = TokenUtil.getUserid(token);
        if (userid == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.findByid(userid);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! TokenUtil.verify(token)) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
