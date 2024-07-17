package com.example.plzfind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plzfind.R;

public class FindWritePost extends AppCompatActivity {

    TextView iii;
    Spinner placeSpinner;
    Button btnWriteFindPost,btnBackFindPost,btnFindRegPic;
    EditText postWriteTitle, edtFindContent;


    // 발견/분실 위치 드롭다운 메뉴 요소들
    String [] places={"학생복지동","진리관","목양관","본부동","체육관","조형관","예술대학동","학교 내","학교 외","통학 버스"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findwritepost);

//        메뉴바 없애는 코드
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        iii=(TextView)findViewById(R.id.iii);
        placeSpinner=(Spinner) findViewById(R.id.placeSpinner); //스피너
        btnWriteFindPost=(Button) findViewById(R.id.btnWriteFindPost);
        btnBackFindPost=(Button) findViewById(R.id.btnBackFindPost);
        postWriteTitle=(EditText)findViewById(R.id.postWriteTitle);
        edtFindContent=(EditText)findViewById(R.id.edtFindContent);

        //등록 버튼 누르면 동작
        btnWriteFindPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = postWriteTitle.getText().toString();
                String content = edtFindContent.getText().toString();
                //String user_id = getIntent().getStringExtra("user_id");

                //테스트토스트
                Toast.makeText(FindWritePost.this, "제목: "+title+"내용"+content, Toast.LENGTH_SHORT).show();//테스트
            }
        }); //btnWriteFindPost

//        뒤로 버튼 누르면 테스트 페이지로 되돌아가기
        btnBackFindPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Test.class); // 테스트 페이지 인텐트
                startActivity(intent);
            }
        }); //btnBackFindPost

    }//onCreate()
}//FindWritePost

