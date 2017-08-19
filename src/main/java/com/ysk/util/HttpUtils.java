package com.ysk.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
@Component
public class HttpUtils {
    public String doGet(String url, String...param) {
        String result = "";
        BufferedReader in = null;
        System.setProperty("jsse.enableSNIExtension", "false");
        try {
            String urlstring = url;
            for(String s:param){
                urlstring+=s;
            }
//            System.out.println(urlstring);
            URL realUrl = new URL(urlstring);
            URLConnection connection = realUrl.openConnection();
//          connection.setRequestProperty("accept", "*/*");
//          connection.setRequestProperty("connection", "Keep-Alive");
//          connection.setRequestProperty("user-agent",
//                  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送get请求失败" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public String doPost(String testUrl, String param) {
//        System.out.println(testUrl);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        System.setProperty("jsse.enableSNIExtension", "false");
        try {
            URL url = new URL(testUrl);
            URLConnection urlConnection = url.openConnection();
//			urlConnection.setRequestProperty("accept", "*/*");
//			urlConnection.setRequestProperty("connection", "Keep-Alive");
//			urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            out = new PrintWriter(urlConnection.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            System.out.println("发送POST请求失败");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
