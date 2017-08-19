package com.ysk.interceptor;

import com.ysk.dao.UserDao;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Y.S.K on 2017/8/2 in wx_bot.
 */
public class LoginedInterceptor implements HandlerInterceptor {
    @Resource
    private UserDao userDao;
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String username = "";
        String password = "";
        if(httpServletRequest.getSession().getAttribute("username")!=null&&httpServletRequest.getSession().getAttribute("password")!=null){
            username=httpServletRequest.getSession().getAttribute("username").toString();
            password=httpServletRequest.getSession().getAttribute("password").toString();
        }
        if(!"".equals(username)&&!"".equals(password)&&password.equals(userDao.queryUserByName(username).getPassword())){
            return true;
        }else {
            httpServletResponse.sendRedirect("/login");
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
