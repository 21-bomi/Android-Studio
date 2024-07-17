package com.example.plzfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Sidebar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar);
        setupMenuButton();
        setupMainButton();
        setupFindButton();
        setupButton1();
        setupButton2();
    }


    private void setupMenuButton() {
        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Sidebar 액티비티를 닫음
            }
        });
    }

    private void setupMainButton() {
        ImageButton mainButton = findViewById(R.id.btn_main);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 메인 페이지로 이동하는 코드
                Intent intent = new Intent(Sidebar.this,HomePage.class);
                startActivity(intent);
            }
        });
    }

    private void setupFindButton() {
        ImageButton findButton = findViewById(R.id.btn_find);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 마이페이지로 이동하는 코드
                Intent intent = new Intent(Sidebar.this, FindPage.class);
                startActivity(intent);
            }
        });
    }

    private void setupButton1() {
        ImageButton btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // "찾아요" 페이지로 이동하는 코드
                Intent intent = new Intent(Sidebar.this, LookPage.class);
                startActivity(intent);
            }
        });
    }

    private void setupButton2() {
        ImageButton btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // "발견했어요" 페이지로 이동하는 코드
                Intent intent = new Intent(Sidebar.this, FindPage.class);
                startActivity(intent);
            }
        });
    }
}
