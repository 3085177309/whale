package com.lcydl.whale.manage.realm;

import com.lcydl.whale.common.pojo.User;
import com.lcydl.whale.manage.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权
     * @param prin
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection prin) {
        /*SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();*/
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();    //获取用户名
        String password = new String((char[]) token.getCredentials());  //获取密码
        // 根据用户名查询用户信息
        User user = userService.get(username);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        // 密码校验
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码不正确!");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}