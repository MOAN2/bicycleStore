package com.example.bicyclestore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.bicyclestore.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}
