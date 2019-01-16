package com.syj.mybatis.dao;

import com.syj.mybatis.model.User;

import java.util.List;

/**
 * @author snailshen
 * @description ${description}
 * @create 2019-01-15 14:45
 */
public interface UserDao {
     void addUser(User user);
     User findUserById(int userId);
     List<User> selectAll();
}
