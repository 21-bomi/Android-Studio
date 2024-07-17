package com.example.plzfind;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView postWriteTitle;
    private TextView edtFindContent;
    private TextView bRegdateTextView, bPlaceTextView;
    private ImageView imageView; // 이미지 뷰 추가

    // ok 버튼 정의 추가
    Button ok;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        postWriteTitle = findViewById(R.id.postWriteTitle);
        edtFindContent = findViewById(R.id.edtFindContent);
        bRegdateTextView = findViewById(R.id.bRegdateTextView);
        bPlaceTextView = findViewById(R.id.bPlaceTextView);
        imageView = findViewById(R.id.imageView);
        // ok 버튼 초기화 추가
        ok = findViewById(R.id.ok);

        Intent intent = getIntent();
        PostItem selectedItem = (PostItem) intent.getSerializableExtra("selectedItem");

        // 각 TextView에 값 설정
        postWriteTitle.setText(selectedItem.getTitle());
        edtFindContent.setText(selectedItem.getContent());
        bRegdateTextView.setText(selectedItem.getRegDate());
        bPlaceTextView.setText(selectedItem.getPlace());

        // 이미지 리소스 가져오기
        int imageResource = getResources().getIdentifier(selectedItem.getImageName(), "drawable", getPackageName());
        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
        } else {
            // 이미지 리소스를 찾을 수 없을 때 기본 이미지 설정
            imageView.setImageResource(R.drawable.menu);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 리스트 목록으로 돌아가는 Intent 생성
                Intent backToListIntent = new Intent(DetailActivity.this, FindPage.class);
                // 새로운 액티비티 시작
                startActivity(backToListIntent);
                // 현재 액티비티 종료
                finish();
            }
        });
    }
}
