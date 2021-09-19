package com.example.u_daily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.DBInterface.DBUser;

/*
    Main activity for login
 */
public class MainActivity extends AppCompatActivity {

    private String userName,psw,spPsw,md5Psw;

    //to connect the button in xml
    private Button login, register, find_psd;

    //for get the info from the edit fame
    private EditText et_user_name,et_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        // get info from activity_main.xml
        et_user_name= findViewById(R.id.userName);
        et_psw = findViewById(R.id.psd);
        login=findViewById(R.id.log_button);
        register = findViewById(R.id.register);
        find_psd = findViewById(R.id.find_psd);

        /**
         * 1. set the register button event
         */
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });


        /**
         * 2. set the forget_password button event
         */
        find_psd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, ForgetPsw.class);
                startActivityForResult(intent,1);
            }
        });

        /**
         * 3. set the log_button event
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();

                //encrypt the password with MD5
                md5Psw= MD5.md5(psw);

                //read the password in file based on the username
                //spPsw = getSharedPreferences("loginInfo", MODE_PRIVATE).getString(userName,"");
                spPsw = DBUser.getPassword(userName);

                //empty username
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(MainActivity.this, "You forgot to enter user name", Toast.LENGTH_SHORT).show();
                }
                //empty password
                else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(MainActivity.this, "You forgot to enter password", Toast.LENGTH_SHORT).show();
                }
                //correct password
                else if(md5Psw.equals(spPsw)){
                    Myinfo.username=userName;
                    Myinfo.identity=DBUser.getIdentity(userName);
                    Intent intent=new Intent(MainActivity.this,Enter.class);
                    startActivityForResult(intent,1);
                }

                // user not exists
                else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
                    Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
                //incorrect password
                else{
                    Toast.makeText(MainActivity.this, "User not exists",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
