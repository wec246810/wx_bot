package com.ysk.dto;

import org.springframework.stereotype.Component;

/**
 * Created by Y.S.K on 2017/8/2 in wx_bot.
 */
//返回json
public class LoginResult {
    private boolean success;
    private String error;

    public LoginResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", error='" + error + '\'' +
                '}';
    }
}
