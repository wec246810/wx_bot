package com.ysk.controller;

import com.ysk.dao.UserDao;
import com.ysk.dto.EUDataGridResult;
import com.ysk.dto.LoginResult;
import com.ysk.entity.SendRecord;
import com.ysk.entity.User;
import com.ysk.service.Impl.UserServiceImpl;
import com.ysk.service.Impl.WxInitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
@Controller
public class tempController {
    private final int autoLoginTimeout = 5 * 365 * 24 * 60 * 60;
    @Autowired
    private UserDao userDao;
    @Autowired
    private WxInitImpl wxInit;
    @Autowired
    HttpSession httpSession;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/index")
    public String index() {
        //进入主页的时候，提前初始化一些东西。
        System.out.println("index........");
        return "index";
    }

    @GetMapping("/login")
    public String login(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (httpServletRequest.getSession().getAttribute("username") != null && !"".equals(httpServletRequest.getSession().getAttribute("username").toString())) {
            return "redirect:/index";
        } else {
            return "login";
        }
    }

    @PostMapping(value = "/check", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResult check(@RequestParam("username") String username, @RequestParam("password") String password
    ) {
        LoginResult result;
        //check表明没有cookies
        //假如登录成功
        if (userDao.queryUserByName(username) != null && userDao.queryUserByName(username).getPassword().equals(password)) {
            //存入cookies
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("password", password);
            result = new LoginResult(true, "0");
            return result;
        } else {
            //登录失败
            result = new LoginResult(false, "用户名或密码错误！");
            return result;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("password");
        return "redirect:/login";
    }

    @GetMapping("/updatepwd")
    public String updatePwd() {
        return "updatepwd";
    }

    /**
     * 发送记录
     *
     * @return
     */
    @GetMapping("/sendRecord/{pageNum}/{pageSize}")
    public String sendRecord(@PathVariable("pageNum")String pageNum,@PathVariable("pageSize")String pageSize,ModelMap ModelMap) {
        int pageNumi=Integer.parseInt(pageNum);
        int pageSizei=Integer.parseInt(pageSize);
        httpSession.setAttribute("pageNum",pageNumi);
        httpSession.setAttribute("pageSize",pageSizei);
        String username=httpSession.getAttribute("username").toString();
        EUDataGridResult euDataGridResult=userService.getSendRecord(pageNumi,pageSizei,username);
        if(euDataGridResult!=null){
            int totalPage=(int)euDataGridResult.getTotal();
            httpSession.setAttribute("totalPage",totalPage);
        }
        return "sendrecord";
    }

    @GetMapping("/mycount")
    public String myCount() {
        return "mycount";
    }

    @GetMapping("/useCDK")
    public String useCDK() {
        return "useCDK";
    }

    @GetMapping("/touse")
    public String toUse() {
        if (userService.getMyTime(httpSession.getAttribute("username").toString()) > 0) {
            return "touse";
        } else {
            return "useCDK";
        }

    }

    @PostMapping(value = "/MyTime", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public LoginResult getMyTime(@RequestParam("username") String username, @RequestParam("password") String password) {
        Long myTime = userService.getMyTime(username);
        if (username != null && myTime > 0) {
            return new LoginResult(true, new Timestamp(myTime).toString());
        } else {
            return new LoginResult(false, "已过期");
        }
    }

    @PostMapping(value = "/UCDK", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public LoginResult UCDK(@RequestParam("cdkValue") String cdkValue, @RequestParam("username") String userName) {
        String resu = userService.useCDK(userName, cdkValue);
        if (resu.contains("成功")) {
            return new LoginResult(true, userName);
        } else if (resu.contains("已被")) {
            return new LoginResult(true, "cdk已被使用");
        } else {
            return new LoginResult(true, "cdk无效");
        }
    }

    //点击生成链接，生成的连接是动态更新二维码的，那么的话，就代表需要一直去生成链接。
    //获得二维码链接,
    @GetMapping("/qrcode/{type}")
    public String getqrcodeurl(@PathVariable("type") String type, ModelMap modelMap) {
        modelMap.put("qrcode", wxInit.qrcodeurl);
        modelMap.put("type", type);
        return "qrcode";
    }

    //    写一个post去实现群发功能，参数有内容，以及群发类型
    @PostMapping(value = "/sendmessage", produces = "application/json;charset=utf-8")
    @ResponseBody
    public void sendMessage( @RequestParam("type") String type) {
        String content=httpSession.getAttribute("content").toString();
        System.out.println("可以进来吧-------------------------------------------");
        synchronized (this) {
            System.out.println("可以进来吧--------------------3233222-------------");
            try {
                wxInit.BeforeWXInit();
                wxInit.wxInit();
                wxInit.getMyFriendList();
                if (type.contains("1")) {
                    //给男的发送消息
                    wxInit.sendMessage(content, wxInit.getMyMan());
                }
                if (type.contains("2")) {
                    //给女的发送消息
                    wxInit.sendMessage(content, wxInit.getMyWoman());
                }
                if (type.contains("3")) {
                    //给群聊发送消息
                    wxInit.sendMessage(content, wxInit.groupList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                wxInit.getQrCode();
            }
        }
    }

    @PostMapping(value = "/getcontent", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getContent(@RequestParam("content") String content) {
        System.out.println("==================" + content);
        httpSession.setAttribute("content", content);
        userService.setSendRecord(httpSession.getAttribute("username").toString(), content);
        wxInit.getQrCode();
        return "1";
    }

    @PostMapping(value = "/updatepassword", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public LoginResult updatepassowrd(@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword) {

        //如果session中username不为空，并且查询旧密码与数据库中密码一致。则修改密码，移除session，转到登录页面
        if (httpSession.getAttribute("username") != null && oldpassword.equals(userDao.queryUserByName(httpSession.getAttribute("username").toString()).getPassword())) {
            //更改密码
            User user = userDao.queryUserByName(httpSession.getAttribute("username").toString());
            user.setPassword(newpassword);
            userDao.updateUserPassWord(user);
            httpSession.removeAttribute("username");
            httpSession.removeAttribute("password");
            return new LoginResult(true, "密码修改成功!");
        } else {
            //无session或用户名密码错误
            System.out.println("更改密码-------------------------");
            return new LoginResult(false, "用户名密码错误!");
        }


    }
    @PostMapping(value = "/getSendRecord", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<SendRecord> getSendRecord(@RequestParam("pageNum")String pageNum,@RequestParam("pageSize") String pageSize){
            int pageNumi=Integer.parseInt(pageNum);
            int pageSizei=Integer.parseInt(pageSize);
             String username=httpSession.getAttribute("username").toString();
             EUDataGridResult euDataGridResult=userService.getSendRecord(pageNumi,pageSizei,username);
             if(euDataGridResult!=null){
                 List<SendRecord>  sendRecords=(List<SendRecord>) euDataGridResult.getRows();
//                 int totalPage=(int)euDataGridResult.getTotal();
//                 httpSession.setAttribute("totalPage",totalPage);
                 return sendRecords;
             }
            return  null;


    }


    @GetMapping("/test")
    public String testJstl() {
        return "testJstl";
    }

}
