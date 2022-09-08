package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.example.recite.Manager.UserDBEngine;
import com.example.recite.R;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;

    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb_rememberpwd;
    private Button bt_login;
    private Button bt_register;

    private UserDBEngine userDBEngine;



    public void RegisterAction(View view) {//跳转到注册页面
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void MainAction(View view,int id) {//跳转到主界面
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp=getSharedPreferences("config", Context.MODE_PRIVATE);
        initView();//初始化

        userDBEngine=new UserDBEngine(this);

        boolean rememberpwd=sp.getBoolean("rememberpwd",false);

        if(rememberpwd){//是否选择记住密码
            //获取sp里面的name和pwd并保存到EditText
            String name=sp.getString("name","");
            String pwd=sp.getString("pwd","");
            et_name.setText(name);
            et_pwd.setText(pwd);
            cb_rememberpwd.setChecked(true);//记住密码勾选
        }

        else{
            String name=sp.getString("name","");
            et_name.setText(name);
            cb_rememberpwd.setChecked(false);
        }

    }

    //初始化
    private void initView() {
        et_name=findViewById(R.id.et_name);
        et_pwd=findViewById(R.id.et_pwd);
        cb_rememberpwd=findViewById(R.id.cb_rememberpwd);
        bt_login=findViewById(R.id.bt_login);
        bt_register=findViewById(R.id.bt_register);

        //设置监听
        MyOnClickListener l=new MyOnClickListener();
        bt_login.setOnClickListener(l);
        bt_register.setOnClickListener(l);

    }



    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.bt_login:
                    //登录操作
                    String name=et_name.getText().toString().trim();
                    String pwd=et_pwd.getText().toString().trim();
                    int id=userDBEngine.queryidByname(view,name);

                    if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd))
                    {
                        Toast.makeText(LoginActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
                    }
                    else if(id<=0){
                        Toast.makeText(LoginActivity.this,"用户名不存在，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                    else if(!userDBEngine.querypwdByname(view,name).equals(pwd)){//判断用户名和密码是否匹配

                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //是否勾选记住密码
                        if(cb_rememberpwd.isChecked()){
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("name",name);
                            editor.putString("pwd",pwd);
                            editor.putBoolean("rememberpwd",true);
                            editor.apply();
                        }

                        else{
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("name",name);
                            editor.putBoolean("rememberpwd",false);
                            editor.apply();
                        }

                        MainAction(view,id);

                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }

                    break;

                case R.id.bt_register:
                    RegisterAction(view);

                    break;
            }
        }
    }









}