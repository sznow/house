package com.mooc.house.controller;

import com.mooc.house.common.model.User;
import com.mooc.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("user/doList")
    public List<User> getUser(){
        return userMapper.selectUsers();
    }
}
