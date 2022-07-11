package com.example.bicyclestore.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //公共字段填充[insert...]
        metaObject.setValue("email",null);
        metaObject.setValue("tel", 0);
        //BaseContext.getCurrentId()是我自定义的类用来获取每个线程当前登录用户的id值
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //公共字段填充[insert...]
//        metaObject.setValue("email",null);
//        metaObject.setValue("tel", 0);
//        metaObject.setValue("address", null);
//        metaObject.setValue("time", LocalDateTime.now());
    }

//    @Override
//    public void updateFill(MetaObject metaObject) {
//        //公共字段填充[update...]
//        metaObject.setValue("updateTime", LocalDateTime.now());
//
//    }
}

