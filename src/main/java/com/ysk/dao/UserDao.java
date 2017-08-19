package com.ysk.dao;

import com.ysk.entity.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
public interface UserDao {
    User queryUserByID(int id);
    void updateUserPassWord(User user);
    void addUser(User user);
    void deleteUser(int id);
    User queryUserByName(String userName);
    void updateUserExpireTime(User user);
}
