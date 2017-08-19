package com.ysk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Y.S.K on 2017/8/13 in wx_bot.
 */
@Controller
public class thymeleafController {
        @RequestMapping("/home")
        public String toHome(Model model){
            model.addAttribute("home", "这是首页");
            return "home";
        }
}
