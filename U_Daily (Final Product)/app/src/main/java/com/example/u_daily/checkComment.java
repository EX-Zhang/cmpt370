package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import com.example.DBInterface.DBProfessor;
import com.example.DBInterface.DBSubject;
import com.example.DataClass.Comment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    Class for showing the comments of a professor
 */
public class checkComment extends AppCompatActivity {

    String subject_id; // The id of subject we current viewing

    /*
        An inner class for storing the professor info (id and name)
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

    private Spinner professor; // Spinner of professors of current subject

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_comment);

        professor = (Spinner)findViewById(R.id.professor_spinner);  // Get the View

        final MultiAutoCompleteTextView view = (MultiAutoCompleteTextView)findViewById(R.id.CommentView);   // Textview for display

        view.setMovementMethod(ScrollingMovementMethod.getInstance());  // Set the textview to be scrollable

        Intent intent=getIntent();
        subject_id=intent.getStringExtra("subject");    // Get hte subject id from previous activity

        if(Myinfo.isProfessor()){   // If current user is a professor
            professor.setVisibility(View.INVISIBLE);    // Can only check his/her comments
            LinkedList<Comment> comments = DBProfessor.checkComment(Myinfo.username);
            if(!comments.isEmpty()){
                int rate=0;
                for(Comment c: comments){
                    rate+=c.getRate();
                }
                rate /= comments.size();
                view.setText("");
                view.append("\n");
                view.append("Average Rate: "+Integer.toString(rate));
                view.append("\n-----------------");
                for(Comment c: comments){
                    view.append("\n");
                    view.append("Time: "+c.getTime()+"\nRate: "+c.getRate()+"\nContent: "+c.getContent());
                    view.append("\n------------------");
                }
            }
        }
        else{
            professor.setVisibility(View.VISIBLE);
            final TextInputEditText content_input = (TextInputEditText)findViewById(R.id.content_input);
            final Context context = this;

            final ArrayList<ProfessorData> data_list=new ArrayList<ProfessorData>();
            String[] ID_list = DBSubject.getProfessorIDs(subject_id);
            for(String id:ID_list){
                data_list.add(new ProfessorData(id,DBProfessor.getName(id)));
            }

            // Spinner to select the professor
            ArrayAdapter<ProfessorData> arr_adapter = new ArrayAdapter<ProfessorData>(this, android.R.layout.simple_spinner_item, data_list);
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            professor.setAdapter(arr_adapter);

            // Display the comments by the selection
            professor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    final String professor_id=data_list.get(arg2).professor_id;
                    LinkedList<Comment> comments = DBProfessor.checkCommentID(professor_id);
                    if(!comments.isEmpty()){
                        int rate=0;
                        for(Comment c: comments){
                            rate+=c.getRate();
                        }
                        rate /= comments.size();
                        view.setText("");
                        view.append("\n");
                        view.append("Average Rate: "+Integer.toString(rate));
                        view.append("\n-----------------");
                        for(Comment c: comments){
                            view.append("\n");
                            view.append("Time: "+c.getTime()+"\nRate: "+c.getRate()+"\n"+c.getContent());
                            view.append("\n------------------");
                        }
                    }
                }
                public void onNothingSelected(AdapterView<?> parent) {
                    // Another interface callback
                }
            });
        }
    }

    // When the back is clicked, return to the previous activity CMPT_LIST and send back the subject id
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(checkComment.this,course.class);
        Bundle bundle=new Bundle();
        bundle.putString("subject",subject_id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
