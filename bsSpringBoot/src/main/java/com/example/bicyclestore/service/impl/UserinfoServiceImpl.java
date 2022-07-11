package com.example.bicyclestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bicyclestore.mapper.UserinfoMapper;
import com.example.bicyclestore.pojo.Userinfo;
import com.example.bicyclestore.service.UserinfoService;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {
}
