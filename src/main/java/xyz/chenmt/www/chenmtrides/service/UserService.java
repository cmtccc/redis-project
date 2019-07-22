package xyz.chenmt.www.chenmtrides.service;

import xyz.chenmt.www.chenmtrides.entity.User;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/07/22 14:40
 */
public interface UserService {

    void save(User user);


    void update(User user);

    User getById(Long id);

    void delete(Long id);


}
