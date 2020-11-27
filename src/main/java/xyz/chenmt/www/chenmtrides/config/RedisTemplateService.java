package xyz.chenmt.www.chenmtrides.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program chenmt-rides
 * @description: redis配置
 * @author: chenmet
 * @create: 2019/07/22 11:36
 */
@Service
public class RedisTemplateService{



//redis有一个问题，就是在存对象的时候，会序列化成2进制文件。但是我们需要的是存的json格式的文件，这样方便我们测试，调试。而一般解决这个问题的方法，是需要在配置类中对这个类进行一些相关的配置，但是这样对每个类都要配置比较麻烦。
//这里我们采用别的方法，存的时候采用阿里巴巴的json对对象转化成json的String类型，然后存进去，取得时候需要传入转换成的类类型。具体方法看下面
//
//作者：流年划破容颜_cc55
//链接：https://www.jianshu.com/p/fd65156ff630
//来源：简书
//简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public <T> boolean set(String key ,T value,Long time){

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if(val==null||val.length()<=0){
                return false;
            }
            stringRedisTemplate.opsForValue().set(key,val,time, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
    * 功能描述:返回键的时间
    *
    * @param  key  键值
    * @return java.lang.Long
    * @author 陈猛涛
    * @date   2020/11/26 10:28
    */
    public Long getExpire(String key){
        try {
            return stringRedisTemplate.getExpire(key,TimeUnit.SECONDS);
        }catch (Exception e){
            return 0l;
        }
    }


    /**
    * 功能描述:获取key是否存在
    *
    * @param  key   键
    * @return boolean
    * @author 陈猛涛
    * @date   2020/11/26 10:23
    */
    public <T> boolean hasKey(String key){
        try {
            return stringRedisTemplate.hasKey(key);
        }catch (Exception e){
            return false;
        }
    }

    public <T> T get(String key,Class<T> clazz){
        try {
            String value = stringRedisTemplate.opsForValue().get(key);

            return stringToBean(value,clazz);
        }catch (Exception e){
            return null ;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String value, Class<T> clazz) {
        if(value==null||value.length()<=0||clazz==null){
            return null;
        }

        if(clazz ==int.class ||clazz==Integer.class){
            return (T)Integer.valueOf(value);
        }
        else if(clazz==long.class||clazz==Long.class){
            return (T)Long.valueOf(value);
        }
        else if(clazz==String.class){
            return (T)value;
        }else {
            return JSON.toJavaObject(JSON.parseObject(value),clazz);
        }
    }

    /**
     *
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {

        if(value==null){
            return null;
        }
        Class <?> clazz = value.getClass();
        if(clazz==int.class||clazz==Integer.class){
            return ""+value;
        }
        else if(clazz==long.class||clazz==Long.class){
            return ""+value;
        }
        else if(clazz==String.class){
            return (String)value;
        }else {
            return JSON.toJSONString(value);
        }
    }

}
