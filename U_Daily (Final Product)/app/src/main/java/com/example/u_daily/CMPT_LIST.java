package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.DBInterface.DBSubject;

/*
    Class for generating the buttons of subjects
 */
public class CMPT_LIST extends AppCompatActivity {
    //Button cmpt370;
    LinearLayout buttons_layout;    // Layout to store the buttons
    Button[] buttons;   //  Buttons
    String main_subject;    // current main subject
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmpt__list);
        //cmpt370 = (Button)findViewById(R.id.cs370);
        /*cmpt370.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CMPT_LIST.this,course.class);
                startActivity(intent);
            }
        });*/

        Intent intent=getIntent();
        main_subject=intent.getStringExtra("MainID");   // Get the main subject ID

        String[] subjects = DBSubject.getSubjectID(main_subject);   // Get the subject id of current main subject

        int subject_num = subjects.length;

        buttons=new Button[subject_num];

        buttons_layout = (LinearLayout)findViewById(R.id.buttons);

        for(int i=0;i<subject_num;i++){ // Generate the buttons automatically

            Button btn = new Button(this);

            final String subject_id = subjects[i];

            btn.setText(subject_id);    // Set the button text to be the subject id( subject name)
            btn.setId(i);
            btn.setOnClickListener(new View.OnClickListener() { // Set the click listener of the button
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CMPT_LIST.this,course.class); // Start the activity_course
                    Bundle bundle=new Bundle();
                    bundle.putString("subject",subject_id); // Pass the subject id to the activity_course
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            buttons[i]=btn;

            buttons_layout.addView(buttons[i]); // Add the button to the layout
        }

    }

    /*
        When back is clicked, return to the activity_subject_list
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CMPT_LIST.this,Subject_list.class);
        startActivity(intent);
    }


}
