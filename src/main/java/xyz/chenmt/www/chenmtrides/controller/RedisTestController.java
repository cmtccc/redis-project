package xyz.chenmt.www.chenmtrides.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chenmt.www.chenmtrides.config.RedisTemplateService;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/07/22 11:54
 */
@RestController
public class RedisTestController {


    @Autowired
    RedisTemplateService redisTemplateService;


    @PostMapping("set")
    public String setRedis(String str,Long time){
        redisTemplateService.set(str,str+System.currentTimeMillis(),time);
        return "保存成功 k为："+str;
    }

    @GetMapping("get")
    public String getRedis(String str){
        return str+":"+redisTemplateService.get(str,str.getClass());
    }

    @GetMapping("hasKey")
    public Boolean hasKey(String str){
        return redisTemplateService.hasKey(str);
    }

    @GetMapping("getExpire")
    public Long getExpire(String str){
        return redisTemplateService.getExpire(str);
    }

}
