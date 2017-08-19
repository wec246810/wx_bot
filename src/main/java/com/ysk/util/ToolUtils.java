package com.ysk.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Y.S.K on 2017/8/3 in wx_bot.
 */
@Component
public class ToolUtils {
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static long orderNum = 0l;
    private static String date ;
    //生成CDK
    public String creatCDK(int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }
    //生成订单编号
    public   String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        int x=1+(int)(Math.random()*5000);
        return str+""+x;
    }

}
