package com.example.bicyclestore.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("bicycle")
public class Bicycle {
    private int id;
//    自行车名称
    private String title;
//    价格
    private int price;
//    类型
    private String type;
//    库存
    private int stock;
//   图片链接
    private String imgUrl;
//    类别  成人车0/儿童车1
    private int sort;
}
