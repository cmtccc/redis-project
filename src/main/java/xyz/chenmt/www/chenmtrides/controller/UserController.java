package xyz.chenmt.www.chenmtrides.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import xyz.chenmt.www.chenmtrides.config.RedisTemplateService;
import xyz.chenmt.www.chenmtrides.entity.User;
import xyz.chenmt.www.chenmtrides.service.UserService;
import xyz.chenmt.www.chenmtrides.utils.BaseResponse;

import java.util.concurrent.TimeUnit;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/07/22 14:38
 */
@RequestMapping("user")
@RestController
@Api(tags = "用户信息接口")
public class UserController {


    public static final String USER_KEY = "User_";

    @Autowired
    RedisTemplateService redisTemplateService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

//    @Autowired
//    StringRedisTemplate stringRedisTemplate;


    @ApiOperation("保存用户")
    @PostMapping("save")
    public BaseResponse<Long> save(@RequestBody User user) {
        userService.save(user);
        stringRedisTemplate.opsForValue().set(USER_KEY + user.getId(), JSON.toJSONString(user), 30L, TimeUnit.SECONDS);
//        redisTemplateService.set(USER_KEY+user.getId(),user);
        return BaseResponse.success(user.getId());
    }

    @ApiOperation("修改用户")
    @PostMapping("update")
    public BaseResponse<Long> update(@RequestBody User user) {
        userService.update(user);
        stringRedisTemplate.delete(USER_KEY + user.getId());
        return BaseResponse.success(user.getId());
    }

    @ApiOperation("修改用户")
    @GetMapping("getById")
    public BaseResponse<User> getById(@RequestParam("id") Long id) {
        User us = redisTemplateService.get(USER_KEY + id, User.class);
        if (null == us) {
            us = userService.getById(id);
            stringRedisTemplate.opsForValue().set(USER_KEY + id, JSON.toJSONString(us), 30L, TimeUnit.SECONDS);
        }
        return BaseResponse.success(us);
    }


}
