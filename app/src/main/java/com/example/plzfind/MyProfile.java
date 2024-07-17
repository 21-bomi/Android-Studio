package com.example.plzfind;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {

    TextView userName;
    EditText edtId, edtNickName, edtEmail;
    Button btnEdt, btnEdtSave, btnEdtCancle;

    //화면 들어오면 edttext들과 이름에 데이터베이스 값 추가, edttext는 수정불가.(setText).
    //-> btnEdtSubmit, btnEdtCancle는 Gone 속성을 통해 없는 것으로 처리함
    //-> btnEdt 클릭 시 btnEdt가 Gone처리되고, 나머지 두 버튼은 visivble으로 활성화됨 && edtText도 입력 활성화됨
    //-> btnEdtSubmit 버튼 클릭시 입력한 데이터가 DB로 넘어가고, btnEdtSubmit, btnEdtCancle는 Gone, btnEdt는 visible
    //   btnEdtCancle 버튼 클릭시 다시 DB데이터를 불러오고(원상복귀) btnEdtSubmit, btnEdtCancle는 Gone, btnEdt는 visible처리.

    // 상수값으로 설정된 user_id
    private static final String USER_ID = "bufind";

    // PHP 스크립트 URL
//    private static final String URL_GET_USER_INFO = "http://bufind.dothome.co.kr/EdtProfile.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        userName = (TextView) findViewById(R.id.profileUserName);
        edtId = (EditText) findViewById(R.id.edtId);
        edtNickName = (EditText) findViewById(R.id.edtNickName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnEdt = (Button) findViewById(R.id.btnEdt);
        btnEdtSave = (Button) findViewById(R.id.btnEdtSave);
        btnEdtCancle = (Button) findViewById(R.id.btnEdtCancle);

        edtId.setText("bufind");

        btnEdtSave.setVisibility(View.GONE);
        btnEdtCancle.setVisibility(View.GONE);

        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //수정버튼 클릭시 수정버튼을 없애고 확인과 취소 버튼을 활성화함
                btnEdt.setVisibility(View.GONE);
                btnEdtSave.setVisibility(View.VISIBLE);
                btnEdtCancle.setVisibility(View.VISIBLE);

                edtNickName.setEnabled(true);
                edtEmail.setEnabled(true);


                btnEdtSave.setOnClickListener(new View.OnClickListener() { //확인버튼 클릭
                    // 클릭시 확인, 취소 버튼을 없애고 수정 버튼을 활성화함
                    @Override
                    public void onClick(View view) {

                        String nName = edtNickName.getText().toString();
                        String email = edtEmail.getText().toString();


                        edtNickName.setText(nName);
                        edtEmail.setText(email);

                        edtNickName.setEnabled(false);
                        edtEmail.setEnabled(false);

                        btnEdt.setVisibility(View.VISIBLE);
                        btnEdtSave.setVisibility(View.GONE);
                        btnEdtCancle.setVisibility(View.GONE);

                        Toast.makeText(MyProfile.this, "사용자 정보가 업데이트되었습니다.", Toast.LENGTH_SHORT).show();
                    } //btnEdtSave-onClick()
                }); //btnEdtSave()

                btnEdtCancle.setOnClickListener(new View.OnClickListener() { //취소버튼 클릭
                    // 클릭시 확인, 취소 버튼을 없애고 수정 버튼을 활성화함
                    @Override
                    public void onClick(View view) {

                        edtNickName.setText("찾아조");
                        edtEmail.setText("plzfind@bu.ac.kr");

                        edtNickName.setEnabled(false);
                        edtEmail.setEnabled(false);


                        //settext로 다시 db데이터로 되돌리기

                        btnEdt.setVisibility(View.VISIBLE);
                        btnEdtSave.setVisibility(View.GONE);
                        btnEdtCancle.setVisibility(View.GONE);
                    } //btnEdtCancle-onClick()
                }); //btnEdtCancle()

            } //btnEdt-onClick()
        }); //btnEdt()

    }//onCreate()
} //MyProfile


