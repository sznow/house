package com.mooc.house.controller;

import com.mooc.house.aop.ControllerWebLog;
import com.mooc.house.common.model.User;
import com.mooc.house.mapper.UserMapper;
import com.mooc.house.service.UserService;
import com.sun.istack.internal.logging.Logger;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("user/doList")
    @ApiOperation("接口日志测试")
    @ControllerWebLog(name = "接口日志请求测试",intoDb = true)
    public List<Map<String,Object>> getUser(){
        logger.info("handle");
        return userService.getUsers();
    }
}
