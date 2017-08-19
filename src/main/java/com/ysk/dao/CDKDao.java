package com.ysk.dao;

import com.ysk.entity.CDK;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Y.S.K on 2017/8/3 in wx_bot.
 */
@Component("cdkDao")
public interface CDKDao {
    void  addCDK(CDK cdk);
    void  deleteCDKByValue(String value);
    List<CDK> queryAllCDK();
    CDK  queryCDKByValue(String value);
    void upadteCDK(CDK cdk);
}
