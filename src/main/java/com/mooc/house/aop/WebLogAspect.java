package com.mooc.house.aop;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.sun.org.glassfish.external.statistics.impl.StatisticImpl.START_TIME;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger   = LoggerFactory.getLogger(getClass());

//  execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)
    @Pointcut("execution(* com.mooc.house.controller..*.*(..))")
    public void webLog(){

    }

    @Before(value = "webLog()&&@annotation(controllerWebLog)")
    public void doBefore(JoinPoint joinPoint,ControllerWebLog controllerWebLog){
        // 开始时间。
//        long startTime = System.currentTimeMillis();
//        Map<String, Object> threadInfo = new HashMap<>();
//        threadInfo.put(START_TIME, startTime);
//        // 请求参数。
//        StringBuilder requestStr = new StringBuilder();
//        Object[] args = joinPoint.getArgs();
//        if (args != null && args.length > 0) {
//            for (Object arg : args) {
//                requestStr.append(arg.toString());
//            }
//        }
//        threadInfo.put(REQUEST_PARAMS, requestStr.toString());
//        threadLocal.set(threadInfo);
        logger.info("before method log done");

    }

    @AfterReturning(value = "webLog()&&@annotation(controllerWebLog)",returning = "res")
    public void doAfterReturning(ControllerWebLog controllerWebLog,Object res){
//        Map<String,Object> threadInfo = threadLocal.get();

    }





}
