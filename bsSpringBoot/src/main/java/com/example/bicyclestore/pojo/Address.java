package com.example.bicyclestore.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("")
public class Address {
    private int id;
    //    用户id
    private int userId;
    //  地址
    private String address;


}
