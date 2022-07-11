package com.example.bicyclestore.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cart")
public class Cart {
    private int id;
    //    用户id
    private int userId;
    //    车id
    private int bicycleId;
    //  车名称
    private String title;
    //  图片链接
    private String imgUrl;
    //    车数量
    private int number;
    //   价格
    private int price;
    //    类型
    private String type;

}
