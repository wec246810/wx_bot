package com.ysk.entity;

import java.sql.Timestamp;

/**
 * Created by Y.S.K on 2017/8/2 in wx_bot.
 */
public class CDK {
     private Integer id;
     private String value;
     private Timestamp createTime;
     private Timestamp useTime;
     private int delayDay;
     private String  whoUse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public int getDelayDay() {
        return delayDay;
    }

    public void setDelayDay(int delayDay) {
        this.delayDay = delayDay;
    }

    public String getWhoUse() {
        return whoUse;
    }

    public void setWhoUse(String whoUse) {
        this.whoUse = whoUse;
    }


}
