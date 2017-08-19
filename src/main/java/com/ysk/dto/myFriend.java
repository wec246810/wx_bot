package com.ysk.dto;

import org.springframework.stereotype.Component;

/**
 * Created by Y.S.K on 2017/8/3 in wx_bot.
 */
@Component
public class myFriend {
    private String userName;
    private int sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "myFriend{" +
                "userName='" + userName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
