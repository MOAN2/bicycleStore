package com.example.bicyclestore.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("myorder")
public class Order {
    private int id;

    //前端获取填充
    //    用户id
    private int userId;
    //    车id
    private int bicycleId;
    //    发货状态  0未发货/1发货
    private int state;
    //    价格
    private int price;
    //车名称
    private String title;

    //用户填写表单获取
    //   收获人姓名
    private String name;
    //    收货地址
    private String address;
    //    收货电话
    private String tel;
    //    送达时间
    private String time;
    //    是否外送  0自取/1外送
    private int isDelivery;
    //    备注

    private String remark;
}
