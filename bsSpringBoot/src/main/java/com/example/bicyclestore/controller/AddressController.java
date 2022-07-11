package com.example.bicyclestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bicyclestore.base.Result;
import com.example.bicyclestore.base.ResultCode;
import com.example.bicyclestore.pojo.Address;
import com.example.bicyclestore.pojo.Bicycle;
import com.example.bicyclestore.pojo.Cart;
import com.example.bicyclestore.service.AddressService;
import com.example.bicyclestore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
    @Autowired
    public AddressService addressService;

    //通过userId来获取address信息
    @GetMapping("/getListByUserId")
    public Result<String> getListBySort(@RequestParam(value="userId") int userId){
        //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过userId来获取address信息");
        QueryWrapper wapper = new QueryWrapper();
        wapper.eq("userId",userId);

        //查询列表
        List<Bicycle> list = addressService.list(wapper);
        if (list.size()>0){
//            列表有值
            return new Result(ResultCode.SUCCESS,list);
        }else{
            return new Result(202,"there is no eligible data",null);
        }
    };

    //添加一个地址
    @PostMapping("/addAddress")
    public Result<String> addAddress(@RequestBody Address address){
        System.out.println("添加一个地址");
        // TableId 注解存在更新记录，否插入一条记录
        boolean state = addressService.saveOrUpdate(address);
        System.out.println(state);
        if (state){
            return new Result(ResultCode.SUCCESS,null);
        }else {
            return new Result(ResultCode.FAIL,null);
        }
    };

}
