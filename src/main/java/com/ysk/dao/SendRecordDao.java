package com.ysk.dao;

import com.ysk.entity.SendRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Y.S.K on 2017/8/2 in wx_bot.
 */

public interface SendRecordDao {
        void addSendRecord(SendRecord sendRecord);
        List<SendRecord> querySendRecordByUserName(String userName);
        List<SendRecord> queryAllSendRecord();
        List<SendRecord> querySendRecordByPage(int start,int size);
}
