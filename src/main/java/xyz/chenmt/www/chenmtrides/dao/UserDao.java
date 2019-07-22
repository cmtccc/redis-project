package xyz.chenmt.www.chenmtrides.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import xyz.chenmt.www.chenmtrides.entity.User;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/07/22 14:30
 */
@Mapper
public interface UserDao {



    /**
     *  @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
     * @param user 用户信息
     * @return
     */
    @CachePut(value = "user", key = "#root.args[0]", unless = "#user eq null ")
    @Insert("insert into user (username, password) values(#{username},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void save(User user);

    /**
     *  @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
     * @param user 用户信息
     * @return
     */
    @CachePut(value = "user", key = "#root.args[0]", unless = "#user eq null ")
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void update(User user);

    /**
     *  @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     *            key 缓存在redis中的key
     *            unless 表示条件表达式成立的话不放入缓存
     * @param id 主键id
     * @return
     */
    @Cacheable("user")
    @Select("select * from user where id=#{id}")
    User getById(Long id);

    /**
     * @CacheEvict 应用到删除数据的方法上，调用方法时会从缓存中删除对应key的数据
     *      condition 与unless相反，只有表达式为真才会执行。
     * @param id 主键id
     * @return
     */
    @CacheEvict(value = "user", key = "#root.args[0]", condition = "#result eq true")
    @Delete("delete from user where id=#{id}")
    void delete(Long id);


}
