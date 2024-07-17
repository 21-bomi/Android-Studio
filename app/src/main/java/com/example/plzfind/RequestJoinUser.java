package com.example.plzfind;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RequestJoinUser extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://bufind.dothome.co.kr/Register.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RequestJoinUser(String user_id, String user_pw, String email, String user_name, String nickname, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("user_pw", user_pw);
        map.put("email", email);
        map.put("user_name", user_name);
        map.put("nickname", nickname);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    } //StringRequest()
} //AppcompatActivity