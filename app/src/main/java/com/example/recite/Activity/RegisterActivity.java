package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recite.Manager.UserDBEngine;
import com.example.recite.R;



public class RegisterActivity extends AppCompatActivity {


    private EditText et_name1;
    private EditText et_pwd1;

    private Button bt_register1;
    private Button bt_exit3;

    private UserDBEngine userDBEngine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();//初始化

        userDBEngine=new UserDBEngine(this);


    }


    //跳转到主页，并将新注册的用户传递过去
    public void MainAction(View view, int id){
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //跳转到登录页面
    public void LoginAction(View view){

        Intent intent=new Intent(this,LoginActivity.class);

        startActivity(intent);

    }



    private void initView() {
        et_name1=findViewById(R.id.et_name1);
        et_pwd1=findViewById(R.id.et_pwd1);
        bt_register1=findViewById(R.id.bt_register1);
        bt_exit3=findViewById(R.id.bt_exit3);

        //设置监听
        RegisterActivity.MyOnClickListener l=new RegisterActivity.MyOnClickListener();
        bt_register1.setOnClickListener(l);
        bt_exit3.setOnClickListener(l);
    }



    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.bt_register1:
                    //注册操作
                    String name=et_name1.getText().toString().trim();
                    String pwd=et_pwd1.getText().toString().trim();

                    int id=userDBEngine.queryidByname(view,name);

                    if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd))
                    {
                        Toast.makeText(RegisterActivity.this,"信息填写不完整",Toast.LENGTH_SHORT).show();
                    }

                    else if(id>0)
                    {
                        Toast.makeText(RegisterActivity.this,"已存在相同名字的用户，请更换用户名",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        userDBEngine.insertUser(view,name,pwd,1,1,1,1,1,1,1,1,1,"",0);
                        int id1=userDBEngine.queryidByname(view,name);
                        Toast.makeText(RegisterActivity.this,"注册成功，自动跳转到主界面",Toast.LENGTH_SHORT).show();
                        MainAction(view,id1);
                    }

                    break;




                case R.id.bt_exit3:

                    LoginAction(view);

                    break;









            }
        }
    }










}