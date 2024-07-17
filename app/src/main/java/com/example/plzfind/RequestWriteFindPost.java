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


public class RequestWriteFindPost extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://bufind.dothome.co.kr/FindPostWrite.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RequestWriteFindPost(String user_id, String title, String content, String b_regdate, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("title", title);
        map.put("content", content);
        map.put("b_regdate", b_regdate);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    } //StringRequest()
} //AppcompatActivity
