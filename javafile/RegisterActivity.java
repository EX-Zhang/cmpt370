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

public class RegisterActivity extends AppCompatActivity {

    // register button
    private Button btn_register;
    //username, paw, psw_again
    private EditText et_user_name,et_psw,et_psw_again;
    //the transfer of these edittext
    private String userName,psw,pswAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        init();
    }

    private void init() {
        //get information from register_page.xml
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);
        et_psw_again=findViewById(R.id.et_psw_again);
        btn_register=findViewById(R.id.btn_register);

        //register button click
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // translate the information and store them
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                pswAgain = et_psw_again.getText().toString().trim();

                //check these information
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "Enter password again", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "the passwords are not the same", Toast.LENGTH_SHORT).show();
                    return;
                    /**
                     *check if the username exists
                     */
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this, "the username already exist", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this, "successful", Toast.LENGTH_SHORT).show();
                    //store username and psw
                    saveRegisterInfo(userName, psw);

                    // send the username back to main page
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    /**
     * function used to check if the username exist
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;

        // "loginInfo"
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //get psw
        String spPsw=sp.getString(userName, "");//
        //if not empty
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
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        // key is username, value is the psw
        editor.putString(userName, md5Psw);
        editor.apply();
    }
}
