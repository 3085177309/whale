package com.lcydl.whale.manage.realm;

import com.lcydl.whale.common.mapper.UserMapper;
import com.lcydl.whale.common.pojo.User;
import com.lcydl.whale.common.pojo.UserExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;


    /**
     * 授权
     * @param prin
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection prin) {
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
        // 查询用户信息
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        User user = userMapper.selectByExample(example).get(0); //根据用户名查询用户信息
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确!");
        }
        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确!");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}