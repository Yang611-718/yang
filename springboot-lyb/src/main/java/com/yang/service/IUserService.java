package com.yang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.pojo.User;

public interface IUserService extends IService<User> {

    public boolean findByEmail(String email);

    public String generatorCheckCode(String email);

    public boolean findByUserName(String userName);

    public boolean register(User user);

    public User login(String userName,String password);

}
