package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.DBInterface.DBOperate;
import com.example.DataClass.Comment;
import com.google.android.material.textfield.TextInputEditText;

import com.example.DBInterface.DBProfessor;

import java.util.Date;

public class rateProfessor extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText rate;
    TextInputEditText content;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_professor);

        name = (TextInputEditText)findViewById(R.id.textInputEditText2);
        rate = (TextInputEditText)findViewById(R.id.textInputEditText3);
        content = (TextInputEditText)findViewById(R.id.textInputEditText);
        submit = (Button)findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rateProfessor.this,rateProfessor.class);
                startActivity(intent);

                final String[] name_arr = name.getText().toString().split(" ");

                final String comment = content.getText().toString();


                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        String professor_id = DBProfessor.getProfessorID(name_arr);

                        if(professor_id==null){
                            System.out.println("Professor name incorrect");
                        }
                        else{
                            Comment.InsertComment(professor_id,"TestUser", DBOperate.getCurrentDate(),comment,Integer.parseInt(rate.getText().toString()));
                        }
                    }
                }).start();


            }
        });
    }

}
