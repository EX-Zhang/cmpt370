package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
    Class of myinfo
 */
public class Myinfo extends AppCompatActivity {

    Button btn1,btn2;

    public static String username;  // Store the current username
    public static String identity;  // Store the current identity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        btn1=(Button)findViewById(R.id.discussion_info);
        btn2=(Button)findViewById(R.id.quickpost_info);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myinfo.this,mydiscussion.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myinfo.this,postdiscussion.class);
                startActivity(intent);
            }
        });
    }

    /*
        Return whether current user is a professor
     */
    protected static boolean isProfessor(){
        return identity.equals("Professor");
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Myinfo.this,Enter.class);
        startActivity(intent);
    }
}
