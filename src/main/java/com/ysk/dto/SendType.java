package com.ysk.dto;

import org.springframework.stereotype.Component;

/**
 * Created by Y.S.K on 2017/8/3 in wx_bot.
 */
public class SendType {
    private boolean  man;
    private boolean  woman;
    private boolean  group;

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public boolean isWoman() {
        return woman;
    }

    public void setWoman(boolean woman) {
        this.woman = woman;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }
}
