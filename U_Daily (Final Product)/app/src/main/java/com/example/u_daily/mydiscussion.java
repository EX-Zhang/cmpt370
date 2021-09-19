package com.example.u_daily;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBInterface.DBDiscussion;
import com.example.DataClass.Discussion;

public class mydiscussion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiscussion);

        final TextView view = findViewById(R.id.post_view);

        view.setMovementMethod(ScrollingMovementMethod.getInstance());

        view.setText("My Discussion:\n");

        for(Discussion d: DBDiscussion.getMyDiscussion()){
            for(String str:d.getString()){
                view.append("\n");
                view.append(str);
                view.append("\n");
            }
            view.append("-------------------------------");
        }

        view.append("\n\nReply to my discussions:");

        for(Discussion d: DBDiscussion.getMyReply()){
            view.append("\n\n");
            for(String str:d.getString()){
                view.append(str);
                view.append("\n");
            }
            view.append("-------------------------------");
        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(mydiscussion.this,Myinfo.class);
        startActivity(intent);
    }
}
