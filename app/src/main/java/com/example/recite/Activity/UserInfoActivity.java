package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recite.Manager.UserDBEngine;

import com.example.recite.R;

public class UserInfoActivity extends AppCompatActivity {

    private UserDBEngine userDBEngine;

    private Button bt_exit1;
    private TextView tv_name1;
    private TextView tv_p1;
    private TextView tv_p2;
    private TextView tv_p3;
    private TextView tv_p4;
    private TextView tv_p5;
    private TextView tv_p6;
    private TextView tv_p7;


    public void MainAction(View view, int id){//跳转到主界面
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initView();//初始化

        userDBEngine=new UserDBEngine(this);

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        String name=userDBEngine.querynameByid(getWindow().getDecorView(),id);
        int p1=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter1")-1;
        int p2=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter2")-1;
        int p3=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter3")-1;
        int p4=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter4")-1;
        int p5=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter5")-1;
        int p6=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter6")-1;
        int p7=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter7")-1;


        tv_name1=findViewById(R.id.tv_name1);
        tv_name1.setText("用户名："+name);

        tv_p1=findViewById(R.id.tv_p1);
        tv_p1.setText("第1章进度： "+p1+"/10");

        tv_p2=findViewById(R.id.tv_p2);
        tv_p2.setText("第2章进度： "+p2+"/30");

        tv_p3=findViewById(R.id.tv_p3);
        tv_p3.setText("第3章进度： "+p3+"/7");

        tv_p4=findViewById(R.id.tv_p4);
        tv_p4.setText("第4章进度： "+p4+"/23");

        tv_p5=findViewById(R.id.tv_p5);
        tv_p5.setText("第5章进度： "+p5+"/11");

        tv_p6=findViewById(R.id.tv_p6);
        tv_p6.setText("第6章进度： "+p6+"/22");

        tv_p7=findViewById(R.id.tv_p7);
        tv_p7.setText("第7章进度： "+p7+"/10");


    }


    private void initView() {

        bt_exit1=findViewById(R.id.bt_exit1);

        //设置监听
        UserInfoActivity.MyOnClickListener l=new UserInfoActivity.MyOnClickListener();
        bt_exit1.setOnClickListener(l);

    }



    private class MyOnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {

            Intent intent=getIntent();
            int id=intent.getExtras().getInt("id");//获取传递来的id

            switch (view.getId()) {

                case R.id.bt_exit1:

                    MainAction(view,id);

                    break;


            }
        }


    }
















}