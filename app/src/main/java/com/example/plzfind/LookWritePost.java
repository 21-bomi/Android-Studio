package com.example.plzfind;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LookWritePost extends AppCompatActivity {

    //private static final String IP_ADDRESS = "bufind.dothome.co.kr";
    //private static final String TAG = "phptest";
    private static final int PERMISSION_CODE = 10;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    EditText postWriteTitle, edtFindContent;
    Button btnWriteFindPost, btnBackFindPost, btnCapture; // 수정: btnFindRegPic -> btnCapture
    ImageView imageView;
    Spinner placeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writepage);

        // 메뉴바 없애는 코드
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        postWriteTitle = findViewById(R.id.Title);
        edtFindContent = findViewById(R.id.Content);
        btnWriteFindPost = findViewById(R.id.btnWriteFindPost);
        btnBackFindPost = findViewById(R.id.btnBackFindPost);
        btnCapture = findViewById(R.id.btnCapture); // 추가: 사진 촬영 버튼
        imageView = findViewById(R.id.imageView);
        placeSpinner = findViewById(R.id.placeSpinner);

        String[] places = {"본부동", "예술동", "지혜관", "복지관", "기숙사", "체육관", "조형관", "학생복지동", "목양관", "백석홀", "인성관", "도서관"};

        // Spinner와 데이터를 연결하는 ArrayAdapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, places);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(adapter);

        btnWriteFindPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = postWriteTitle.getText().toString();
                String content = edtFindContent.getText().toString();
                String bplace = placeSpinner.getSelectedItem().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String currentDateAndTime = sdf.format(new Date());

                // 여기서 데이터 서버로 전송하는 부분을 추가하면 됩니다.

                // 입력 필드 초기화
                postWriteTitle.setText("");
                edtFindContent.setText("");

                Intent intent = new Intent(LookWritePost.this, LookPage.class);
                startActivity(intent);
                finish();
            }
        });

        btnCapture.setOnClickListener(new View.OnClickListener() { // 추가: 사진 촬영 버튼의 클릭 리스너
            @Override
            public void onClick(View view) {
                capture();
            }
        });
    }

    private void capture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permission, PERMISSION_CODE);
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }
}