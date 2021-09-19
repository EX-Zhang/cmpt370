package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CMPT_LIST extends AppCompatActivity {
    Button cmpt370;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmpt__list);
        cmpt370 = (Button)findViewById(R.id.cs370);
        cmpt370.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CMPT_LIST.this,course.class);
                startActivity(intent);
            }
        });

    }


}
