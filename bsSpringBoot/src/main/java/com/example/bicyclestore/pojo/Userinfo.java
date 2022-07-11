package com.example.bicyclestore.pojo;

import com.baomidou.mybatisplus.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class Userinfo {
    private int Id;
//    用户名
    private String username;
//    用户密码
    private String password;
//    用户类型 管理员1/用户0
    private int type;

//    电话 自动填充字段
    @TableField(fill = FieldFill.INSERT)
    private int tel;

//    邮件
    @TableField(fill = FieldFill.INSERT)
    private String email;


}