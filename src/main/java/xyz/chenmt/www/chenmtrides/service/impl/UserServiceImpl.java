package xyz.chenmt.www.chenmtrides.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenmt.www.chenmtrides.dao.UserDao;
import xyz.chenmt.www.chenmtrides.entity.User;
import xyz.chenmt.www.chenmtrides.service.UserService;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/07/22 14:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
