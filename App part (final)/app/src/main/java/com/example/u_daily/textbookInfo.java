package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.DBInterface.DBOperate;

/*
    Class to show the textbook info
 */
public class textbookInfo extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_info);
        TextView view = (TextView)findViewById(R.id.textbook);

        view.setMovementMethod(ScrollingMovementMethod.getInstance());
        back = (Button)findViewById(R.id.backBtn);
        Intent intent=getIntent();
        final String subject_id=intent.getStringExtra("subject");

        String[] textbooks = DBOperate.getTextbookName(subject_id);

        for(int i=0;i<textbooks.length;i++){
            view.append("\n");
            view.append(textbooks[i]);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(textbookInfo.this,course.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    }

