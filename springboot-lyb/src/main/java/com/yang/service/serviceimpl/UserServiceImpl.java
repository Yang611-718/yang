package com.yang.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.IUserService;


import com.yang.utils.MainUtil;
import com.yang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MainUtil mainUtil;

    @Override
    public boolean findByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        List<User> users = userMapper.selectList(wrapper);
        return users.size()>0;
    }

    @Override
    public boolean findByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        System.out.println(username);
        System.out.println(user);
        return user == null;
    }

    @Override
    public String generatorCheckCode(String email) {
        String code = String.valueOf(Util.getRandomNumber(1000, 9999));
        String content = "您的验证码为：" + code;
        System.out.println(content);
        mainUtil.sendCheckCodeEmail(email, content);
        return code;
    }


    @Override
    @Transactional
    public boolean register(User user) {
        int id = userMapper.insert(user);
        System.out.println(user);
        return true;
    }

    @Override
    public User login(String username, String pwd) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (pwd != null && user != null && user.getPwd().equals(pwd)) {
            return user;
        } else {
            return null;
        }
    }
}


