package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
    Class for entering the activity_subject_list or the activity_Myinfo
 */
public class Enter extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        btn1 = (Button)findViewById(R.id.all_subject);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this,Subject_list.class);
                startActivity(intent);
            }
        });

        btn2 = (Button)findViewById(R.id.myinfo);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this,Myinfo.class);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Enter.this,MainActivity.class);
        startActivity(intent);
    }
}
