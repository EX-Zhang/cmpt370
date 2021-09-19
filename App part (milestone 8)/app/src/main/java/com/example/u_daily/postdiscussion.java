package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.DBInterface.DBOperate;
import com.example.DBInterface.DBProfessor;
import com.example.DataClass.Comment;
import com.example.DataClass.Discussion;
import com.google.android.material.textfield.TextInputEditText;

public class postdiscussion extends AppCompatActivity {
    Button submit;

    TextInputEditText type;
    TextInputEditText topic;
    TextInputEditText content;
    TextInputEditText tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdiscussion);
        submit = (Button)findViewById(R.id.submitPos);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(postdiscussion.this,postdiscussion.class);
                startActivity(intent);

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
                        Discussion.insertDiscussion(Type,Topic,tags,Content);
                    }
                }).start();
            }
        });
    }

    public void onClick(View view) {


    }
}

