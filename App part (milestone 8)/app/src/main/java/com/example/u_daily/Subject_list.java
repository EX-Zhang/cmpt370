package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subject_list extends AppCompatActivity {

    Button CMPT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        CMPT = (Button)findViewById(R.id.CMPT_SUB);
        CMPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Subject_list.this,CMPT_LIST.class);
                startActivity(intent);
            }
        });
    }
}
