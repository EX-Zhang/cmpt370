package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.DataClass.Discussion;

import com.google.android.material.textfield.TextInputEditText;

/*
    Class for activity for posting new discussion
 */
public class postdiscussion extends AppCompatActivity {
    Button submit;  // Button to submit
    TextInputEditText type; // Type
    TextInputEditText topic;    // Topic
    TextInputEditText content;  // Content
    TextInputEditText tag;      // Tags
    String subject_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdiscussion);
        submit = (Button)findViewById(R.id.submitPos);
        Intent intent=getIntent();
        subject_id=intent.getStringExtra("subject");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = (TextInputEditText)findViewById(R.id.TypeInput);
                topic =(TextInputEditText)findViewById(R.id.TopicInput);
                tag = (TextInputEditText)findViewById(R.id.TagInput);
                content = (TextInputEditText)findViewById(R.id.contentInput);

                final String Type = type.getText().toString();
                final String Topic = topic.getText().toString();
                final String[] tags = tag.getText().toString().split(" ");
                final String Content = content.getText().toString();

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Discussion.insertDiscussion(Type,Topic,tags,Content,subject_id,Myinfo.username);
                    }
                }).start();

                // After posting, return to the previous activity
                if(subject_id==null){
                    Intent intent = new Intent(postdiscussion.this,Myinfo.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(postdiscussion.this,course.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("subject",subject_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(subject_id==null){
            Intent intent = new Intent(postdiscussion.this,Myinfo.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(postdiscussion.this,course.class);
            Bundle bundle=new Bundle();
            bundle.putString("subject",subject_id);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}

