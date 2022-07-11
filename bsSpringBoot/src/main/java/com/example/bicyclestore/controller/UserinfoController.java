package com.example.bicyclestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bicyclestore.base.Result;
import com.example.bicyclestore.base.ResultCode;
import com.example.bicyclestore.pojo.Bicycle;
import com.example.bicyclestore.pojo.Userinfo;
import com.alibaba.fastjson.JSON;
import com.example.bicyclestore.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserinfoController {
    @Autowired
    public UserinfoService userinfoService;

    //通过id来获取某个用户的user信息
    @GetMapping("/getByUserId")
    public Result<String> getUserById(@RequestParam(name="id") String id){
       //Mybatis-plus 里的方法
        System.out.println("通过id来获取某个用户的user信息");
        Userinfo user = userinfoService.getById(id);
        if (user == null){
            return new Result(ResultCode.FAIL,null);
        }else {
            String data = JSON.toJSONString(user);
            return new Result(ResultCode.SUCCESS,JSON.parse(data));
        }
    };

    //注销用户信息
    @GetMapping("/removeById")
    public Result<String> removeById(@RequestParam(name="id") int id){
        //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过id来删除order信息");
        boolean state = userinfoService.removeById(id);
        if (state){
            return new Result(ResultCode.SUCCESS,null);
        }else {
            return new Result(ResultCode.FAIL,null);
        }
    };

    //通过username获取登录信息
    @GetMapping("/getInfoByName")
    public Result<String> getListBySort(@RequestParam(value="username") String username){
        //Mybatis-plus 里的 根据 Wrapper，查询一条记录 方法
        System.out.println("通过username获取登录信息");
        QueryWrapper wapper = new QueryWrapper();
        wapper.eq("username",username);

        //查询列表
        List<Bicycle> list = userinfoService.list(wapper);
        if (list.size()>0){
//            列表有值 则成功登录
            return new Result(ResultCode.SUCCESS,list);
        }else{
            return new Result(202,"there is no eligible data",null);
        }
    };

    //获取所有用户列表
    @GetMapping("/getAllUser")
    public Result<String> getAllUser(){
        System.out.println("获取所有用户列表");
        // 查询所有
        //List<T> list();

        List<Userinfo> list = userinfoService.list();
        String data = JSON.toJSONString(list);
        return new Result(200,"SUCCESS",JSON.parse(data));

    };

    //添加一个用户，或者修改某个用户的信息
    @PostMapping("/addUser")
    public Result<String> addOneUser(@RequestBody Userinfo userinfo){
        System.out.println("添加用户");
        // TableId 注解存在更新记录，否插入一条记录
        boolean state = userinfoService.saveOrUpdate(userinfo);
        if (state){
            return new Result(ResultCode.SUCCESS,null);
        }else {
            return new Result(ResultCode.FAIL,null);
        }
    };
}
