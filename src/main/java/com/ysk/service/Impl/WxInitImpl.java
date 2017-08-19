package com.ysk.service.Impl;

import com.alipay.api.internal.util.codec.EncoderException;
import com.ysk.dto.myFriend;
import com.ysk.service.WxInit;
import com.ysk.util.HttpUtils;
import com.ysk.util.WxUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.nio.cs.ext.GBK;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
@Service(value = "wxInit")
public class WxInitImpl implements WxInit {
    private final String UUIDUrl = "https://login.weixin.qq.com/jslogin";
    private final String UUIDParam = "appid=wx782c26e4c19acffb&fun=new&lang=en_CH&_=";
    private final String EWMUrl = "https://login.weixin.qq.com/qrcode/";
    private final String getRedirectUrl = "https://login.wx2.qq.com/cgi-bin/mmwebwx-bin/login";
    private  String getCookiesUrl = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage";
    private  String wxInitUrl = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=";
    private  String wxGetContact = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?r=";
    private  String wxSendTextMessage = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?lang=zh_CN&pass_ticket=";

   //通过下面链接获得skey等值
   // https://login.wx2.qq.com/cgi-bin/mmwebwx-bin/login?loginicon=true&uuid=wdDksTeRMw==&tip=0&r=1305988661&_=1501932499251
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private WxUtils wxUtils;
    //我的单个朋友朋友
    @Autowired
    private myFriend myFriend;
    //UUID
    private String uuid = "";
    //初始化所需要的wxsid.wxuin，passticket等信息的Map
    private Map initMap = new HashMap<String, String>();
    //全部的群聊，最近+通讯录
    public List groupList = new ArrayList<String>();
    //自己的UserName
    private String myUserName = "";
    //我的全部朋友列表(带性别)
    List myFriends = new ArrayList<myFriend>();
    //二维码链接
    public String qrcodeurl = "";
    //判断是否轮询结束
    static  boolean end=false;

    /**
     * 获取微信二维码链接
     *
     * @return
     */
    public String getQrCode() {
        //getUUID
        String sr = httpUtils.doPost(UUIDUrl,UUIDParam);
        uuid = sr.split("\"")[1].toString();
        System.out.println("获取UUID完毕");
        //获取二维码
        System.out.println(EWMUrl + uuid);
        qrcodeurl = EWMUrl + uuid;
        return EWMUrl + uuid;
    }

    /**
     * 获得微信初始化之前所需的参数，可能被的多次调用，先存到map
     *
     * @return
     */
    public Map BeforeWXInit () {
        String redirectUrl = "";
        String temp = "";
        System.out.println("等待用户扫描");
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        while (!temp.contains("window.code=200")&&!temp.contains("window.code=200")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp = httpUtils.doGet(getRedirectUrl, "?tip=1&uuid=" + uuid + "&_=");
            if (temp.contains("window.code=200")) {
                end=true;
                System.out.println("用户登陆完成");
            }
        }
        while (true){
            if(end){
                redirectUrl = temp.split("\"")[1];
                System.out.println("得到重定向连接完毕");
                String results="";
                if(redirectUrl.contains("wx.qq")){
                    results = httpUtils.doGet(redirectUrl,"&fun=new&version=v2");
                    System.out.println(results);
                }else{
                    results=httpUtils.doGet(redirectUrl,"&fun=new&version=v2&lang=zh_CN");
                }
                System.out.println("获取sid,skey,uin等完毕");
                Document dom;
                try {
                    dom = DocumentHelper.parseText(results);
                    Element root = dom.getRootElement();
                    String skey = root.element("skey").getText();
                    String wxsid = root.element("wxsid").getText();
                    String wxuin = root.element("wxuin").getText();
                    String pass_ticket = root.element("pass_ticket").getText();
                    initMap.put("skey", skey);
                    initMap.put("wxsid", wxsid);
                    initMap.put("wxuin", wxuin);
                    initMap.put("pass_ticket", pass_ticket);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                System.out.println("获取sid,skey,uin等已放入map");
                System.out.println(redirectUrl);
                if(redirectUrl.contains("wx2.qq")){
                    getCookiesUrl=   getCookiesUrl.replaceAll("wx.qq","wx2.qq");
                    wxInitUrl= wxInitUrl.replaceAll("wx.qq","wx2.qq");
                    wxGetContact= wxGetContact.replaceAll("wx.qq","wx2.qq");
                    wxSendTextMessage= wxSendTextMessage.replaceAll("wx.qq","wx2.qq");
                }
                System.out.println(initMap);
                break;
            }

        }
        return initMap;
    }

    public void wxInit() {
        System.out.println(wxInitUrl);
        //https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=1305633400&lang=zh_CN&pass_ticket=DQOObfCJSeYIh2QikKHp%252FuFEmMFTlNhfY5R28dINJTs%252BlfYluI84lumEDSSIA5nZ
        //微信初始化
        String initResult = httpUtils.doPost(wxInitUrl + System.currentTimeMillis() + "&lang=ch_ZN&pass_ticket=" + initMap.get("pass_ticket"),
                "{\"BaseRequest\":{\"Uin\":\""
                        + initMap.get("wxuin")
                        + "\",\"Sid\":\""
                        + initMap.get("wxsid")
                        + "\",\"Skey\":\""
                        + initMap.get("skey")
                        + "\",\"DeviceID\":\""
                        + wxUtils.getDeviceID()
                        + "\"}}");

        System.out.println(initResult);
        //通过微信初始化获得最近朋友列表
        JSONArray tempJsonArray = new JSONArray();
//        System.out.println(initResult);
            tempJsonArray = JSONObject.fromObject(initResult).getJSONArray("ContactList");
        System.out.println("tempJsonArray:"+tempJsonArray);
        Iterator<Object> it = tempJsonArray.iterator();
        JSONObject tempJSONObject = new JSONObject();
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
            if (ob.get("UserName").toString().contains("@@")) {
                groupList.add(ob.get("UserName").toString());
            }
        }
        //获得自己的UserName
        JSONObject json;
            json = JSONObject.fromObject(initResult);
        JSONObject json1 = JSONObject.fromObject(json.get("User"));
//        System.out.println(json1);
        myUserName = (String) json1.get("UserName");
        System.out.println(myUserName);
        System.out.println("得到自己的UserName");
    }

    //获得好友列表
    public List<myFriend> getMyFriendList() {
        String myfriendsresult = httpUtils.doPost(wxGetContact + System.currentTimeMillis() + "&pass_ticket=" + initMap.get("pass_ticket"), "{\"BaseRequest\":{\"Uin\":\""
                + initMap.get("wxuin")
                + "\",\"Sid\":\""
                + initMap.get("wxsid")
                + "\",\"Skey\":\""
                + initMap.get("skey")
                + "\",\"DeviceID\":\""
                + wxUtils.getDeviceID()
                + "\"}}");
        JSONObject json = JSONObject.fromObject(myfriendsresult);
//        System.out.println(myfriendsresult);

        for (int i = 0; i < Integer.parseInt(json.get("MemberCount").toString()); i++) {
            com.ysk.dto.myFriend tempmyFriend = new myFriend();
            JSONObject tempJsonObject = JSONObject.fromObject(json.getJSONArray("MemberList").get(i));
            //获得好友中的群组
            if ((tempJsonObject.get("UserName").toString().contains("@@"))) {
                //好友列表中的群组添加到grouplist
                groupList.add(tempJsonObject.get("UserName").toString());
            }
            tempmyFriend.setUserName(tempJsonObject.get("UserName").toString());
            tempmyFriend.setSex(Integer.parseInt(tempJsonObject.get("Sex").toString()));
//            System.out.println(tempmyFriend);
            myFriends.add(tempmyFriend);
        }
        System.out.println(myFriends);
        System.out.println("得到所有的朋友的UserName完毕");
        return myFriends;
    }


    public List getMyMan() {
        List myMan = new ArrayList<String>();
        for (int i = 0; i < myFriends.size(); i++) {
            myFriend = (myFriend) myFriends.get(i);
            if (myFriend.getSex() == 1) {
                myMan.add(myFriend.getUserName().toString());
            }
        }
        System.out.println(myMan);
        return myMan;
    }

    public List getMyWoman() {
        List myWomanMan = new ArrayList<String>();
        for (int i = 0; i < myFriends.size(); i++) {
            myFriend = (myFriend) myFriends.get(i);
            if (myFriend.getSex() == 2) {
                myWomanMan.add(myFriend.getUserName().toString());
            }
        }
        System.out.println(myWomanMan);
        return myWomanMan;
    }

    public void sendMessage(String content, List To)  {
         //不知道为啥，这里得转成GBK格式才可以
        //1.原格式解码
        //2.想要的格式进行编码
        System.out.println(content+"-------------------");
        try {
            byte[] t_gbk = content.getBytes("UTF-8");
            content = new String(t_gbk, "GBK");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        //测试代码
        List<String> a=new ArrayList<>();
        a.add("filehelper");
        To=a;
        //---------------------------------------------------------------
        System.out.println(content);
        System.out.println(To);
        long stamp = wxUtils.getMsgId();
        for (int i = 0; i < To.size(); i++) {
            httpUtils.doPost(wxSendTextMessage + initMap.get("pass_ticket"),
                    "{\"BaseRequest\":{\"DeviceID\" : \""
                            + wxUtils.getDeviceID()
                            + "\",\"Sid\" : \""
                            + initMap.get("wxsid")
                            + "\",\"Skey\" : \""
                            + initMap.get("skey")
                            + "\",\"Uin\" : "
                            + initMap.get("wxuin")
                            + "},\"Msg\" : {\"Type\" : "
                            + "1"
                            + ",\"Content\" : \""
                            + content
//                            + "测试哈"
                            + "\",\"FromUserName\" : \""
                            + myUserName
                            + "\",\"ToUserName\" : \""
                            + To.get(i).toString()
//				+allUerName.get(i)
                            + "\",\"LocalID\" : \""
                            + stamp
                            + "\",\"ClientMsgId\" : "
                            + stamp
                            + "}}");
            System.out.println(wxSendTextMessage + initMap.get("pass_ticket"));
            System.out.println("{\"BaseRequest\":{\"DeviceID\" : \""
                    + wxUtils.getDeviceID()
                    + "\",\"Sid\" : \""
                    + initMap.get("wxsid")
                    + "\",\"Skey\" : \""
                    + initMap.get("skey")
                    + "\",\"Uin\" : "
                    + initMap.get("wxuin")
                    + "},\"Msg\" : {\"Type\" : "
                    + "1"
                    + ",\"Content\" : \""
                    + content
                    + "\",\"FromUserName\" : \""
                    + myUserName
                    + "\",\"ToUserName\" : \""
                    + To.get(i).toString()
//				+allUerName.get(i)
                    + "\",\"LocalID\" : \""
                    + stamp
                    + "\",\"ClientMsgId\" : "
                    + stamp
                    + "}}");
        }
        System.out.println("发送信息完毕");
    }



}
