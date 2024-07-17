package com.example.plzfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Test extends AppCompatActivity {

    Button btntest, btntestprofile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmain);
        Intent intent = getIntent();
        String logValue = intent.getStringExtra("log");

        btntest=(Button) findViewById(R.id.btntest);
        btntestprofile=(Button) findViewById(R.id.btntestprofile);

        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id = getIntent().getStringExtra("user_id");
                Intent intent = new Intent(Test.this, FindWritePost.class);
                startActivity(intent);
                intent.putExtra("user_id", user_id);

                Toast.makeText(Test.this, "유저 아이디: "+user_id, Toast.LENGTH_SHORT).show();

            }
        }); //onClick

        btntestprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test.this, MyProfile.class);
                startActivity(intent);
            }
        });

    } //onCreate()
} //MainActivity