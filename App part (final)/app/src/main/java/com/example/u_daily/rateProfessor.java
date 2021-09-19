package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBInterface.*;
import com.example.DataClass.Comment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/*
    Class for activity of rate professor
 */
public class rateProfessor extends AppCompatActivity {
    Button submit;
    String subject_id;

    /*
        Inner class for store the professor info
     */
    private class ProfessorData{

        String professor_id;
        String professor_name;

        public ProfessorData(String id, String name){
            professor_id=id;
            professor_name=name;
        }

        public String toString(){
            return professor_name;
        }
    }

    /*
        A container to store the pos of selection of the professor for using in a new thread
     */
    private class PosContainer{

        private int pos;

        private boolean empty=true;

        public PosContainer(){}

        public void setPos(int pos){ this.pos=pos;empty=false; }

        public int getPos(){return pos;}

        public boolean isEmpty(){return empty;}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_professor);
        submit = (Button)findViewById(R.id.submitBtn);

        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        simpleRatingBar.setNumStars(5);

        final Spinner name_input = (Spinner)findViewById(R.id.name_spinner);    // Spinner of professors of current subject
        final TextInputEditText content_input = (TextInputEditText)findViewById(R.id.content_input);
        final Context context = this;

        Intent intent=getIntent();
        subject_id=intent.getStringExtra("subject");    // Get the subject from previous activity

        final ArrayList<ProfessorData> data_list=new ArrayList<ProfessorData>();
        String[] ID_list = DBSubject.getProfessorIDs(subject_id);   // Get the professors of current subject
        for(String id:ID_list){
            data_list.add(new ProfessorData(id,DBProfessor.getName(id)));
        }

        ArrayAdapter<ProfessorData> arr_adapter = new ArrayAdapter<ProfessorData>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name_input.setAdapter(arr_adapter);


        final PosContainer ID_container=new PosContainer(); // Container

        name_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                ID_container.setPos(pos);   // When a professor is selected, store the pos in the container
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(rateProfessor.this,course.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id);
                intent.putExtras(bundle);
                startActivity(intent);

                if(!ID_container.isEmpty()){    // If the container is not empty, insert the data
                    //final String[] name_arr = name_input.getText().toString().split(" ");
                    final int pos=ID_container.getPos();

                    final int rate = (int)simpleRatingBar.getRating();
                    final String comment = content_input.getText().toString();

                    new Thread(new Runnable(){  // Start a new thread for insertion
                        @Override
                        public void run() {
                            //String professor_id = DBProfessor.getProfessorID(name_arr);
                            String professor_id = data_list.get(pos).professor_id;

                            if(professor_id==null){
                                Toast.makeText(context, "Professor name incorrect", Toast.LENGTH_SHORT).show();
                            }
                            else if(Myinfo.username.equals(DBProfessor.getUsername(professor_id))){
                                Toast.makeText(context, "You Cannot Rate Yourself", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Comment.InsertComment(professor_id,Myinfo.username, DBOperate.getCurrentDate(),comment,rate);
                            }
                        }
                    }).start();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(rateProfessor.this,course.class);
        Bundle bundle=new Bundle();
        bundle.putString("subject",subject_id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
