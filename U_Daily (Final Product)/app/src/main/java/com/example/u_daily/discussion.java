package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.DBInterface.DBDiscussion;
import com.example.DataClass.Discussion;

import static java.lang.Thread.sleep;

/*
    Class for the activity_discussion
 */
public class discussion extends AppCompatActivity {
    Button newPost; // Button for posting a new discussion
    String subject_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        newPost = (Button)findViewById(R.id.newPost);

        final TextView view = findViewById(R.id.post_view);

        view.setMovementMethod(ScrollingMovementMethod.getInstance());

        Intent intent=getIntent();
        subject_id=intent.getStringExtra("subject");

        view.setText("");

        for(Discussion d: DBDiscussion.getNewestPost()){    // Get the newest post when the activity is created
            view.append("\n\n");
            for(String str:d.getString()){
                view.append(str);
                view.append("\n");
            }
            view.append("-------------------------------");
        }

        newPost.setOnClickListener(new View.OnClickListener() { // When the button is clicked, start the activity_postdiscussion
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(discussion.this,postdiscussion.class);
                Bundle bundle=new Bundle();
                bundle.putString("subject",subject_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(discussion.this,course.class);
        Bundle bundle=new Bundle();
        bundle.putString("subject",subject_id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
