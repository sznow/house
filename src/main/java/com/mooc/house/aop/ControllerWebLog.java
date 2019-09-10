package com.mooc.house.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControllerWebLog {
    //调用接口名称
    String name();
    //标识该条操作日志是否需要持久化存储
    boolean intoDb() default false;

}
