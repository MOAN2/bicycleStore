package com.example.bicyclestore;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.example.bicyclestore.mapper")
class BicycleStoreApplicationTests {

    @Test
    void contextLoads() {
    }

}
