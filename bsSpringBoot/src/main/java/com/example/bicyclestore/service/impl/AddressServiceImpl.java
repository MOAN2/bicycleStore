package com.example.bicyclestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bicyclestore.mapper.AddressMapper;
import com.example.bicyclestore.pojo.Address;
import com.example.bicyclestore.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
