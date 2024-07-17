package com.example.plzfind;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText logId, logPw;
    private Button btnLogin,btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        메뉴바 없애는 코드
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        logId=(EditText) findViewById(R.id.logId);
        logPw=(EditText) findViewById(R.id.logPw);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnJoin=(Button)findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(new View.OnClickListener() {//회원가입 버튼을 클릭시 수행
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,JoinUser.class);
                startActivity(intent);
            }
        }); //btnJoin()

        btnLogin.setOnClickListener(new View.OnClickListener() { //로그인 버튼 클릭시 수행
            @Override
            public void onClick(View view) {
                final String user_id=logId.getText().toString();
                String user_pw=logPw.getText().toString();


                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject=new JSONObject(response);
                            boolean success=jasonObject.getBoolean("success");
                            if (success) {//로그인 성공한 경우
                                String user_id = jasonObject.getString("user_id");
                                String user_pw = jasonObject.getString("user_pw");

                                Toast.makeText(getApplicationContext(), "로그인 성공2", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, HomePage.class);
                                intent.putExtra("log", "User"); // Test 액티비티로 전환될 때, "log"라는 키(key)로 "User"라는 값을 담은 데이터를 전달
                                intent.putExtra("user_id", user_id);
                                intent.putExtra("user_pw", user_pw);
                                startActivity(intent);

//                                SharedPreferences preferences = getSharedPreferences("userLoginID", MODE_PRIVATE);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.putString("user_id", user_id);
//                                editor.apply();

                            } //if-로그인성공
                            else{//로그인 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;

                            } //else-로그인실패
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest=new LoginRequest(user_id,user_pw,responseListener);
                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        }); //

    }//MainActivity
}