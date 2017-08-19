package com.ysk.service;

import com.ysk.dto.EUDataGridResult;
import com.ysk.entity.SendRecord;

import java.util.List;

/**
 * Created by Y.S.K on 2017/8/3 in wx_bot.
 */
public interface UserService {
    Long  getMyTime(String username);
    List<SendRecord> getMySendRecord(String username);
//    boolean  expire(String username);
    String  useCDK(String username,String cdkvalue);
//    void updatePassword(String username);
//    void toUse(String username);
    void setSendRecord(String username,String content);
//    List<SendRecord> getSendRecord(int pageNum,int pageSize,String username);
    EUDataGridResult getSendRecord(int pageNum, int pageSize, String username);

    //接口中可以有默认方法实现jdk1.8新特性，默认方法可以直接被调用，不用实现
    default int add(int a,int b){
        return a+b;
    }
}
