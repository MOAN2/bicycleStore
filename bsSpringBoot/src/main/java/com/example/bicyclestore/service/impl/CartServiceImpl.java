package com.example.bicyclestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.bicyclestore.mapper.CartMapper;
import com.example.bicyclestore.pojo.Cart;
import com.example.bicyclestore.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
}
