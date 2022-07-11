package com.example.bicyclestore.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bicyclestore.base.Result;
import com.example.bicyclestore.base.ResultCode;
import com.example.bicyclestore.pojo.Bicycle;
import com.example.bicyclestore.pojo.Userinfo;
import com.example.bicyclestore.service.BicycleService;
import com.example.bicyclestore.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bicycle")
public class BicycleController {
    @Autowired
    public BicycleService bicycleService;

    //通过sort类别（成人/儿童）来获取bicycle信息
    @GetMapping("/getListBySort")
    public Result<String> getListBySort(@RequestParam(value="sort") int sort){
       //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过sort类别（成人/儿童）来获取 bicycle信息");
        QueryWrapper wapper = new QueryWrapper();
        wapper.eq("sort",sort);

        //查询列表
        List<Bicycle> list = bicycleService.list(wapper);
        if (list.size()>0){
//            列表有值
            return new Result(ResultCode.SUCCESS,list);
        }else{
            return new Result(202,"there is no eligible data",null);
        }
    };

    //通过type类型（死飞车/山地车/折叠车/青年车/小童车/儿童车）来获取bicycle信息
    @GetMapping("/getListByType")
    public Result<String> getListByType(@RequestParam(value="type") String type){
        //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过type类型（死飞车/山地车/折叠车/青年车/小童车/儿童车）来获取bicycle信息"+type);
        QueryWrapper wapper = new QueryWrapper();
        wapper.eq("type",type);

        //查询列表
        List<Bicycle> list = bicycleService.list(wapper);
        if (list.size()>0){
//            列表有值
            return new Result(ResultCode.SUCCESS,list);
        }else{
            return new Result(202,"there is no eligible data",null);
        }
    };

    //获取所有车列表
    @GetMapping("/getAllBicycle")
    public Result<String> getAllBicycle(){
        System.out.println("获取所有车列表");
        // 查询所有
        //List<T> list();
        List<Bicycle> list = bicycleService.list();
        String data = JSON.toJSONString(list);
        return new Result(200,"SUCCESS",JSON.parse(data));

    };

}
