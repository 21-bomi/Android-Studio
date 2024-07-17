package com.example.plzfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinUser extends AppCompatActivity {

    private EditText joinName, joinNickname, joinId, joinPw,joinPwCheck,joinEmail;
    private Button btnIdCheck,btnLogin;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.signup);

        //        메뉴바 없애는 코드
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //아이디값 찾아주기
        joinName = findViewById(R.id.joinName );
        joinNickname = findViewById(R.id.joinNickname);
        joinId = findViewById(R.id.joinId);
        joinPw = findViewById(R.id.joinPw);
        joinPwCheck = findViewById(R.id.joinPwCheck);
        joinEmail = findViewById(R.id.joinEmail);


        //아이디 중복 체크
        btnIdCheck = findViewById(R.id.btnIdCheck);
        btnIdCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user_id = joinId.getText().toString();
                if (validate) {
                    return; //검증 완료
                }

                if (user_id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinUser.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                } //아이디 입력을 안했을 때

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinUser.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                joinId.setEnabled(false); //아이디값 고정
                                validate = true; //검증 완료
                               // btnIdCheck.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinUser.this);
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                IdDoubleCheck idDoubleCheck = new IdDoubleCheck(user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinUser.this);
                queue.add(idDoubleCheck);
            }
        });



        //회원가입 버튼 클릭 시 수행
        btnLogin = findViewById( R.id.btnLogin );
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user_id = joinId.getText().toString();
                final String user_pw = joinPw.getText().toString();
                final String email = joinEmail.getText().toString();
                final String user_name = joinName.getText().toString();
                final String nickname = joinNickname.getText().toString();
                final String PassWordCk = joinPwCheck.getText().toString();
                //@@DB랑 edittext의 문자열이랑 연동


                //아이디 중복체크 했는지 확인
                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinUser.this);
                    dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //한 칸이라도 입력 안했을 경우
                if (user_id.equals("") || user_pw.equals("") || email.equals("") || user_name.equals("") || nickname.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinUser.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(user_pw.equals(PassWordCk)) {
                                if (success) {

                                    Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다. 로그인해주세요.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(JoinUser.this, MainActivity.class);
                                    startActivity(intent);

                                    //회원가입 실패시
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinUser.this);
                                dialog = builder.setMessage("비밀번호가 동일하지 않습니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                RequestJoinUser registerRequest = new RequestJoinUser(user_id, user_pw, email, user_name, nickname, responseListener);
                RequestQueue queue = Volley.newRequestQueue( JoinUser.this );
                queue.add( registerRequest );
            }
        });
    }
}