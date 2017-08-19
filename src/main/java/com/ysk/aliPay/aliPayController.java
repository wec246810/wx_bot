package com.ysk.aliPay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ysk.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.ysk.aliPay.AlipayConfig.*;

/**
 * Created by Y.S.K on 2017/8/9 in wx_bot.
 */
@Controller
public class aliPayController {
 @Autowired
 private ToolUtils toolUtils;
// @Autowired
// private Goods goods;
    @PostMapping("/CallBack/return_url")
    public String return_url(){
        return "return_url";
    }
    @PostMapping("/CallBack/notify_url")
    public String notify_url(){
        return "notify_url";
    }
    @PostMapping("/paypage")
    public void payPage(@RequestParam("price") double price,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,app_id,merchant_private_key,format,charset,alipay_public_key,sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8080/CallBack/return_url");
        alipayRequest.setNotifyUrl("http://localhost:8080/CallBack/notify_url");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+toolUtils.getOrderNo()+"\"," +       //订单号
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +//商品代码，不用改
                "    \"total_amount\":"+price+"," +                      //商品价格
                "    \"subject\":\"余额充值\"," +                 // 商品标题
                "    \"body\":\"充值余额\"" +                   //  商品描述
//                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//                "    \"extend_params\":{" +
//                "    \"sys_service_provider_id\":\"2088511833207846\"" +
//                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpServletResponse.setContentType("text/html;charset=" + charset);
        try {
            httpServletResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //充值页面
    @GetMapping("/topay")
    public String toPay(){
        return "toPay";
    }

}
