package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recite.Manager.AdditionDBEngine;
import com.example.recite.Manager.CommandDBEngine;
import com.example.recite.Manager.ReviewDBEngine;
import com.example.recite.Manager.UserDBEngine;
import com.example.recite.R;


public class ExampleActivity extends AppCompatActivity {


    private TextView tv_example;
    private Button bt_break3;

    private CommandDBEngine commandDBEngine;
    private UserDBEngine userDBEngine;
    private AdditionDBEngine additionDBEngine;
    private ReviewDBEngine reviewDBEngine;


    //跳转到学习界面
    public void StudyAction(View view, int id, int flag) {
        Intent intent=new Intent(this, StudyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //跳转到复习界面
    public void ReviewAction(View view,int id) {
        Intent intent=new Intent(this, ReviewActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        userDBEngine=new UserDBEngine(this);
        commandDBEngine=new CommandDBEngine(this);
        additionDBEngine=new AdditionDBEngine(this);
        reviewDBEngine=new ReviewDBEngine(this);

        initView();

    }




    //初始化
    private void initView() {

        tv_example=findViewById(R.id.tv_example);
        bt_break3=findViewById(R.id.bt_break3);

        //设置监听
        ExampleActivity.MyOnClickListener l=new ExampleActivity.MyOnClickListener();
        tv_example.setOnClickListener(l);
        bt_break3.setOnClickListener(l);


        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        int flag=intent.getExtras().getInt("flag");
        String k=intent.getExtras().getString("command");

        if(flag==1){

            String tb_name=userDBEngine.query_tbnameByid(getWindow().getDecorView(),id);
            int now=userDBEngine.querynowByid(getWindow().getDecorView(),id,tb_name);
            commandDBEngine.open();
            int total=commandDBEngine.queryTotal(getWindow().getDecorView(),tb_name);

            if(now>total){

                now=total;
            }

            commandDBEngine.open();
            String a=commandDBEngine.queryExampleByNum(getWindow().getDecorView(),tb_name,now);


            if(a.contains("\\n")){

                String b=a.replace("\\n","\n");
                tv_example.setText(b);

            }else{

                tv_example.setText(a);
            }
        }else if(flag==2){

            int now=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter8");
            int t=additionDBEngine.queryTotal(getWindow().getDecorView());
            if(now>t){

                now=t;
            }

            String a=additionDBEngine.queryExample(getWindow().getDecorView(),now);

            if(a.contains("\\n")){

                String b=a.replace("\\n","\n");
                tv_example.setText(b);

            }else{

                tv_example.setText(a);
            }


        }else if(flag==3){

            reviewDBEngine.open();
            String a=reviewDBEngine.queryExampleBycommand(getWindow().getDecorView(),k);

            if(a.contains("\\n")){

                String b=a.replace("\\n","\n");
                tv_example.setText(b);

            }else{

                tv_example.setText(a);
            }



        }



    }



    private class MyOnClickListener implements View.OnClickListener{

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        int flag=intent.getExtras().getInt("flag");

        @Override
        public void onClick(View view) {
            switch (view.getId()){


                case R.id.bt_break3:

                    if(flag==1 || flag==2){

                        StudyAction(view,id,flag);
                    }

                    if(flag==3){


                        ReviewAction(view,id);
                    }

                    break;


            }
        }
    }


















}