package com.example.plzfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    ImageButton btnmenu; // ImageButton 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃이 확장된 후에 ImageButton 초기화
        btnmenu = findViewById(R.id.btn_menu_main);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sidebar 액티비티 열기
                Intent intent = new Intent(HomePage.this, Sidebar.class);
                startActivity(intent);
            }
        });
    }

    public void firstLinearLayout(View view) {
        Intent intent;
        intent = new Intent(this, LookPage.class);
        startActivity(intent); // 첫 번째 링크로 이동
    }

    // 두 번째 LinearLayout 클릭 시
    public void secondLinearLayout(View view) {
        Intent intent;
        intent = new Intent(this, FindPage.class);
        startActivity(intent); // 두 번째 링크로 이동
    }
}


