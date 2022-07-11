package com.example.bicyclestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bicyclestore.mapper.BicycleMapper;
import com.example.bicyclestore.pojo.Bicycle;
import com.example.bicyclestore.service.BicycleService;
import org.springframework.stereotype.Service;

@Service
public class BicycleServiceImpl extends ServiceImpl<BicycleMapper, Bicycle> implements BicycleService {
}
