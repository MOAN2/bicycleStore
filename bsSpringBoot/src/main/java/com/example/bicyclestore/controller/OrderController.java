package com.example.bicyclestore.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bicyclestore.base.Result;
import com.example.bicyclestore.base.ResultCode;
import com.example.bicyclestore.pojo.Bicycle;
import com.example.bicyclestore.pojo.Order;
import com.example.bicyclestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    //获取所有订单列表
    @GetMapping("/getAllOrder")
    public Result<String> getAllOrder(){
        System.out.println("获取所有订单列表");
        // 查询所有
        //List<T> list();
        List<Order> list = orderService.list();
        String data = JSON.toJSONString(list);
        return new Result(200,"SUCCESS",JSON.parse(data));

    };

    //通过userId来获取order信息
    @GetMapping("/getListByUserId")
    public Result<String> getListByUserId(@RequestParam(value="userId") int userId){
        //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过userId来获取order信息");
        QueryWrapper wapper = new QueryWrapper();
        wapper.eq("userId",userId);

        //查询列表
        List<Bicycle> list = orderService.list(wapper);
        if (list.size()>0){
//        列表有值
            return new Result(ResultCode.SUCCESS,list);
        }else{
            return new Result(202,"there is no eligible data",null);
        }
    };

    //通过id来删除order信息
    @GetMapping("/removeById")
    public Result<String> removeById(@RequestParam(name="id") int id){
        //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过id来删除order信息");
        boolean state = orderService.removeById(id);
        if (state){
            return new Result(ResultCode.SUCCESS,null);
        }else {
            return new Result(ResultCode.FAIL,null);
        }
    };

    //添加一个订单
    @PostMapping("/addOrder")
    public Result<String> addOrder(@RequestBody Order order){
        System.out.println("添加一个订单");
        // TableId 注解存在更新记录，否插入一条记录
        boolean state = orderService.saveOrUpdate(order);
        System.out.println(state);
        if (state){
            return new Result(ResultCode.SUCCESS,null);
        }else {
            return new Result(ResultCode.FAIL,null);
        }
    };

}
