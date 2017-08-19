package com.ysk.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
@Component
public class WxUtils {
    static int nRecycledMessageID = 0;    // 0000 - 9999

    /**
     * 获得DeviceID
     * @return
     */
    public String getDeviceID() {
        Random rm = new Random();
        double pross = (1 + rm.nextDouble()) * Math.pow(10, 15);
        String fixLenthString = String.valueOf(pross);
        return "e" + fixLenthString.substring(2, 15 + 2);
    }

    /**
     * 获取Msgid
     * @return
     */
    public long getMsgId() {
        if (nRecycledMessageID == 9999)
            nRecycledMessageID = 0;
        return System.currentTimeMillis() * 10000 + (nRecycledMessageID++);
    }

    /**
     *
     * @param urlString url地址
     * @param filename 文件名字
     * @param savePath 存储地址
     * @throws Exception
     */
    public void downloadImg(String urlString, String filename, String savePath) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(5 * 1000);
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }
}
