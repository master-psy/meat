package com.api;

import com.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModuleApiApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        System.out.println("单元测试");
    }

    @Test
    void testRedis() {
        String key = "test_key";
        redisService.setCacheObject(key, "哈哈哈");
        System.out.println((String) redisService.getCacheObject(key));
        redisService.deleteObject(key);
        System.out.println((String) redisService.getCacheObject(key));

    }

}
