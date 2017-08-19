package com.ysk.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysk.cache.RedisDao;
import com.ysk.dao.CDKDao;
import com.ysk.dao.SendRecordDao;
import com.ysk.dao.UserDao;
import com.ysk.dto.EUDataGridResult;
import com.ysk.entity.CDK;
import com.ysk.entity.SendRecord;
import com.ysk.entity.User;
import com.ysk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.java2d.SurfaceDataProxy;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Y.S.K on 2017/8/3 in wx_bot.
 */
@Service(" userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private CDKDao cdkDao;
    @Resource
    private SendRecordDao sendRecordDao;
//    @Autowired
//    private RedisDao redisDao;
    public Long getMyTime(String username) {
//        User user=redisDao.getUser(username);
//        if(user==null){
//            user=userDao.queryUserByName(username);
//            System.out.println("-------------------------"+user);
//            if(user!=null){
//                redisDao.putUser(user);
//                user=redisDao.getUser(username);
//                System.out.println(user);
//                System.out.println("放入Redis缓存----------------");
//            }
//        }
        User user =  userDao.queryUserByName(username);
        System.out.println(user);
      if(user!=null&&user.getExpirationTime()!=null&&user.getExpirationTime().getTime()>new Date().getTime()){
          return user.getExpirationTime().getTime();
      }else {
          return -1L;
      }
    }

    public List<SendRecord> getMySendRecord(String username) {
        return null;
    }

//    public boolean expire(String username) {
//        long nowtimestamp = new Date().getTime();
//        long mytimestamp = getMyTime(username);
//        if (mytimestamp > 0) {
//            if (nowtimestamp > mytimestamp) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return true;
//        }
//    }
    @Transactional
    public String useCDK(String username,String cdkvalue) {
       //读取数据   cdk的延长时间，cdk的使用时间，
        //将数据更新到数据库 cdk的使用时间，使用者，使用者增加时间
        CDK cdk=cdkDao.queryCDKByValue(cdkvalue);
        User user=userDao.queryUserByName(username);
        Timestamp extimestamp;
        //cdk存在且未被使用
        if(cdk!=null&&cdk.getUseTime()==null){
                int delayDay=cdk.getDelayDay();
                Long delaytime=delayDay*60*60*24*1000L;
            if(Long.parseLong(user.getExpirationTime().toString())>new Date().getTime()){
                //如果过期，则为现在时间加上延长时间
                extimestamp=new Timestamp(new Date().getTime()+delaytime);
            }else {
                //如果没过期，则为到期时间加上延长时间
               extimestamp=new Timestamp(getMyTime(username)+delaytime);
            }
            //更新cdk的使用时间，cdk的使用者，使用者的增加时间
            cdk.setUseTime(new Timestamp(new Date().getTime()));
            cdk.setWhoUse(username);
            cdkDao.upadteCDK(cdk);
            user.setExpirationTime(extimestamp);
            userDao.updateUserExpireTime(user);
            return "使用成功！";
        }else if(cdk!=null&&cdk.getUseTime()!=null){
            return "CDK已被使用";
        }else{
            return "CDK无效";
        }

    }
    //设置发送记录
    @Override
    public void setSendRecord(String username,String content) {
        //用户名，内容，创建时间,
        SendRecord sendRecord=new SendRecord();
        sendRecord.setUserName(username);
        sendRecord.setContent(content);
        sendRecord.setCreateTime(new Timestamp(new Date().getTime()));
        sendRecordDao.addSendRecord(sendRecord);
    }

    @Override
    public EUDataGridResult getSendRecord(int pageNum, int pageSize, String username) {
        //设置分页的参数，第几页，每页多少个
        PageHelper.startPage(pageNum, pageSize);
        //返回的就是传入参数的页数
        List<SendRecord> sendRecords=sendRecordDao.querySendRecordByUserName(username);
        EUDataGridResult result=new EUDataGridResult();
        result.setRows(sendRecords);
        PageInfo pageInfo=new PageInfo(sendRecords);
        result.setTotal(pageInfo.getPages());
        System.out.println(result);
        return result;
//        return sendRecords;
    }


}
