package com.example.blanca;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtUser;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件
        //声明控件
        Button mBtnLogin = findViewById(R.id.btn_login);
        mEtUser = findViewById(R.id.et_1);
        mEtPassword = findViewById(R.id.et_2);

        //实现跳转
        mBtnLogin.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent);
        });

        //匹配对应用户名和密码
        mBtnLogin.setOnClickListener(this);
    }


    public void onClick(View v){
        //获取输入的用户名和密码
        String username = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        Intent intent;

        //假设正确的账号密码分别是zyz，062627
        if(username.equals("zyz")&&password.equals("062627")){
            //正确则跳转
            intent = new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent);
        }//不正确,则弹出登录失败

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}