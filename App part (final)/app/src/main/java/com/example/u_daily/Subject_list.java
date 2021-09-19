package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.DBInterface.DBSubject;

/*
    Class for generating buttons of main subject
 */
public class Subject_list extends AppCompatActivity {

    //Button CMPT;
    LinearLayout buttons_layout;    // Layout to store the buttons
    Button[] buttons;   // buttons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        /*CMPT = (Button)findViewById(R.id.CMPT_SUB);
        CMPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Subject_list.this,CMPT_LIST.class);
                startActivity(intent);
            }
        });*/

        buttons_layout=(LinearLayout)findViewById(R.id.buttons_layout);

        String[] mainIDs = DBSubject.getMainIDs();  // Get main ids

        int main_num=mainIDs.length;

        buttons=new Button[main_num];

        for(int i=0;i<main_num;i++){

            Button btn = new Button(this);

            final String main_id = mainIDs[i];

            btn.setText(main_id);

            btn.setId(i);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {   // Click to start the activity_CMPT_LIST
                    Intent intent = new Intent(Subject_list.this,CMPT_LIST.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("MainID",main_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            buttons[i]=btn;

            buttons_layout.addView(buttons[i]);

        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Subject_list.this,Enter.class);
        startActivity(intent);
    }
}
