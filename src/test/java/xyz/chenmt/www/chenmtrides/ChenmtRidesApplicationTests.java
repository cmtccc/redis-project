package xyz.chenmt.www.chenmtrides;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.chenmt.www.chenmtrides.config.RedisTemplateService;
import xyz.chenmt.www.chenmtrides.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChenmtRidesApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplateService redisTemplateService;

    @Test
    public void test()  {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");

    }

    @Test
    public void redisTest(){

        User user = new User();
        user.setId(11L);
        user.setUsername("test");
        user.setPassword("hello redis");
        redisTemplateService.set("key1",user);

        User us = redisTemplateService.get("key1",User.class);
        System.out.println(us.getUsername()+":  "+us.getPassword());
    }

}
