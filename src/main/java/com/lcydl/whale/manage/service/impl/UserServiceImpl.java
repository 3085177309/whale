package com.lcydl.whale.manage.service.impl;

import com.lcydl.whale.common.mapper.UserMapper;
import com.lcydl.whale.common.pojo.User;
import com.lcydl.whale.common.pojo.UserExample;
import com.lcydl.whale.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
