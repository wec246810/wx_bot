package com.ysk.service;

import com.ysk.dto.myFriend;
import com.ysk.entity.SendRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
public interface WxInit {
    //    //通过固定链接得到
//    String getUUID();
//    //创建二维码，通过UUID获得
//    void creatEWM();
//    //得到重定向链接，通过UUID获得
//    void  getRedirect();
//    //获取sid,skey,uin等信息
//    void getCookies();
//    void getSid();
//    //wx初始化
//    //获得全部联系人信息 ，然后区分性别存入到Mapper中
//    //获得自己的信息
//    //解析提取出自己和朋友列表的UserName
//    //发送信息 参数（userName,）筛选发送的性别
//    void getFriendInfo();
//    void SendMessage();
//    void getUserName();
//    void getMyselfUserName();
//    void getCurrentGroupList();
    //---------------------------------------------------------------------------------------------
    //微信登陆前
    String getQrCode();

    //用户扫描二维码后,获得初始化需要的数据存入map
    Map BeforeWXInit();

    //微信初始化操作
    void wxInit();

    //解析获得的数据
    List<myFriend> getMyFriendList();

    //发送信息
    void sendMessage(String content, List type);

    List getMyMan();

    List getMyWoman();


}
