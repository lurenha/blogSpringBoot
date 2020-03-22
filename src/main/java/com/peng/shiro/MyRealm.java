package com.peng.shiro;



import com.peng.entity.User;
import com.peng.service.IUserService;
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


    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
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
        Long userId = TokenUtil.getUserId(principals.toString());
        User user = userService.getById(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(user.getPermissionlist());
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

        User user = userService.getById(userId);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! TokenUtil.verify(token)) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
