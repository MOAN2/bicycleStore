package com.example.bicyclestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.bicyclestore.mapper.OrderMapper;
import com.example.bicyclestore.pojo.Order;
import com.example.bicyclestore.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
