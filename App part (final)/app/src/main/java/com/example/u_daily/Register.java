package com.example.u_daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBInterface.DBUser;

/*
    Class for registering
 */
public class Register extends AppCompatActivity {

    // register button
    public Button btn_register;
    //username, paw, psw_again
    private EditText et_user_name,et_psw,et_psw_again,et_name,et_email,et_university;
    //the transfer of these edittext
    private String userName,psw,pswAgain,name="",email="",university="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        //get information from activity_register.xml
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);
        et_psw_again=findViewById(R.id.et_psw_again);
        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_university=findViewById(R.id.et_university);
        btn_register=findViewById(R.id.btn_register);

        //register button click
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // translate the information and store them
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                pswAgain = et_psw_again.getText().toString().trim();
                name=et_name.getText().toString().trim();
                email=et_email.getText().toString().trim();
                university=et_university.getText().toString().trim();

                //check these information
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Register.this, "Enter username", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(Register.this, "Enter password again", Toast.LENGTH_SHORT).show();
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(Register.this, "the passwords are not the same", Toast.LENGTH_SHORT).show();
                }

                /*check if the username exists */
                else if(/*isExistUserName(userName)*/DBUser.userExist(userName)){
                    Toast.makeText(Register.this, "the username already exist", Toast.LENGTH_SHORT).show();
                }

                /* store username and psw */
                else{
                    Toast.makeText(Register.this, "successful", Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(userName, psw);
                    String md5Psw = MD5.md5(psw);
                    DBUser.register(userName,md5Psw,name,email,university);
                    // send the username back to main page
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    Register.this.finish();
                }
            }
        });
    }

    /**
     * function used to check if the username exist
     */
    private boolean isExistUserName(String userName){

        // initially it is false
        boolean has_userName=false;

        // "loginInfo"
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        //get psw
        String spPsw=sp.getString(userName, "");//
        //if psw is not empty
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    /**
     * save username and psw to SharedPreferences
     */
    private void saveRegisterInfo(String userName,String psw){
        // md5 the psw
        String md5Psw = MD5.md5(psw);

        //loginInfo is the file
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        // key is username, value is the psw
        editor.putString(userName, md5Psw);
        editor.apply();
    }

}
