package com.ysk.aliPay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import static com.ysk.aliPay.AlipayConfig.*;

/**
 * Created by Y.S.K on 2017/8/9 in wx_bot.
 */
public class test {
    public static void main(String[] args) {
//        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,app_id,merchant_private_key,format,charset,alipay_public_key,sign_type);
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
//        alipayRequest.setBizContent("{" +
//                "    \"out_trade_no\":\"20150320010101001\"," +
//                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//                "    \"total_amount\":88.88," +
//                "    \"subject\":\"Iphone6 16G\"," +
//                "    \"body\":\"Iphone6 16G\"," +
//                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//                "    \"extend_params\":{" +
//                "    \"sys_service_provider_id\":\"2088511833207846\"" +
//                "    }"+
//                "  }");//填充业务参数
//        String form="";
//        try {
//            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        httpResponse.setContentType("text/html;charset=" + CHARSET);
//        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();



//Thread.interrupted()
//        Thread t=new Thread();
//t.isInterrupted()
//t.interrupt();
//        InterruptedException


    }

}
