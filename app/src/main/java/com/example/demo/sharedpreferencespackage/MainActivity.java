package com.example.demo.sharedpreferencespackage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.sharedpreferencespackage.helper.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userName;
    private EditText password;
    private Button login;
    private CheckBox holdPwd;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * init
     */
    private void init(){
        sharedPreferencesHelper = new SharedPreferencesHelper(
                MainActivity.this,"anhua"
        );
        userName = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        holdPwd = (CheckBox)findViewById(R.id.login_hold);
        holdPwd.setChecked(true);

        userName.setText(sharedPreferencesHelper.getSharedPreference("userName",
                "").toString().trim());
        password.setText(sharedPreferencesHelper.getSharedPreference("password",
                "").toString().trim());

        login = (Button)findViewById(R.id.btn_logon);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_logon:
                loginTest();
                break;
            default:
                break;
        }

    }

    private void loginTest(){
        String name = userName.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if (holdPwd.isChecked()){
            sharedPreferencesHelper.put("username",name);
            sharedPreferencesHelper.put("password",pwd);
            sharedPreferencesHelper.put("isremember",holdPwd.isChecked());
        }else {
            sharedPreferencesHelper.remove("username");
            sharedPreferencesHelper.remove("password");
            sharedPreferencesHelper.remove("isremember");
        }

        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();


    }
}
