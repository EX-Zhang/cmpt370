package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class course extends AppCompatActivity {

     Button btn1,dis,textbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        btn1 = (Button)findViewById(R.id.rateCourse);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,rateProfessor.class);
                startActivity(intent);
            }
        });

        dis = (Button)findViewById(R.id.discussionCourse);
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,discussion.class);
                startActivity(intent);
            }
        });

        textbook = (Button)findViewById(R.id.textbookinfoCourse);
        textbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,textbookInfo.class);
                startActivity(intent);
            }
        });
    }
}
