package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recite.Manager.AdditionDBEngine;
import com.example.recite.Manager.CommandDBEngine;
import com.example.recite.Manager.ReviewDBEngine;
import com.example.recite.Manager.UserDBEngine;
import com.example.recite.R;

public class MainActivity extends AppCompatActivity {


    private Button bt_start;
    private Button bt_review;
    private Button bt_information;
    private Button bt_exit;
    private Button bt_addcommand;


    private UserDBEngine userDBEngine;
    private CommandDBEngine commandDBEngine;
    private AdditionDBEngine additionDBEngine;
    private ReviewDBEngine reviewDBEngine;



    //跳转到登录界面
    public void LoginAction(View view) {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void UserInfoAction(View view,int id) {//跳转到用户信息界面

        Intent intent=new Intent(this, UserInfoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //跳转到选择界面
    private void SelectAction(View view,int id) {


        Intent intent=new Intent(this, SelectActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void ReviewAction(View view,int id) {
        Intent intent=new Intent(this, ReviewActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void AddCommandAction(View view,int id) {//跳转到添加命令界面
        Intent intent=new Intent(this, AddCommandActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();//初始化

        userDBEngine=new UserDBEngine(this);
        commandDBEngine=new CommandDBEngine(this);
        additionDBEngine=new AdditionDBEngine(this);
        reviewDBEngine=new ReviewDBEngine(this);

    }




    private void initView() {

        bt_start=findViewById(R.id.bt_start);
        bt_review=findViewById(R.id.bt_review);
        bt_information=findViewById(R.id.bt_information);
        bt_exit=findViewById(R.id.bt_exit);
        bt_addcommand=findViewById(R.id.bt_addcommand);



        //设置监听
        MainActivity.MyOnClickListener l=new MainActivity.MyOnClickListener();
        bt_start.setOnClickListener(l);
        bt_review.setOnClickListener(l);
        bt_information.setOnClickListener(l);
        bt_exit.setOnClickListener(l);
        bt_addcommand.setOnClickListener(l);



    }


    private class MyOnClickListener implements View.OnClickListener{

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id



        @Override
        public void onClick(View view) {


            switch (view.getId()){

                case R.id.bt_start:

                    SelectAction(view,id);

                    break;

                case R.id.bt_review:

                    int level=userDBEngine.querylevelByid(view,id);
                    if(level>38){
                        userDBEngine.Initlevel(view,id);
                    }
                    ReviewAction(view,id);

                    break;

                case R.id.bt_information:

                    UserInfoAction(view,id);

                    break;

                case R.id.bt_exit:

                    LoginAction(view);

                    break;


                case R.id.bt_addcommand:

                   AddCommandAction(view,id);

                    break;



            }
        }
    }




}