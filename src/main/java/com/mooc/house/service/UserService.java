package com.mooc.house.service;

import com.mooc.house.common.model.User;
import com.mooc.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<Map<String,Object>> getUsers(){
        return userMapper.selectUsers();
    }
}
