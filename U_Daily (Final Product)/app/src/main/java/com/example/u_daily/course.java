package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DBInterface.DBSubject;

/*
    Display the four major functions (rate professor, check textbook, post discussion, check comments) of a subject
 */
public class course extends AppCompatActivity {

    String subject_id;  // Subject ID we current viewing
    Button btn1,dis,textbook,comt;  // Buttons of four functions
    TextView name;  // Textview to display the subject name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        subject_id=intent.getStringExtra("subject"); // Get the subject id from previous activity

        setContentView(R.layout.activity_course);

        name = (TextView)findViewById(R.id.subject_name);
        name.setText(DBSubject.getName(subject_id));    // Display the subject name

        btn1 = (Button)findViewById(R.id.rateCourse);   // Set the click function to start activity_rateProfessor
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,rateProfessor.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id); // Pass the subject id to the activity
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        dis = (Button)findViewById(R.id.discussionCourse);  // Set the click function to start activity_discussion
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,discussion.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id); // Pass the subject id to the activity
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        textbook = (Button)findViewById(R.id.textbookinfoCourse);   // Set the click function to start activity_textbookInfo
        textbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,textbookInfo.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        comt = (Button)findViewById(R.id.checkComment); // Set the click function to start activity_checkComment
        comt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,checkComment.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /*
        When back is clicked, return to the activity_subject_list
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(course.this,CMPT_LIST.class);
        Bundle bundle=new Bundle();
        bundle.putString("MainID",subject_id.split("-")[0]);    // Send back the main id of current subject
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
