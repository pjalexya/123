package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recite.Manager.AdditionDBEngine;
import com.example.recite.Manager.CommandDBEngine;
import com.example.recite.Manager.UserDBEngine;

import com.example.recite.R;

public class StudyActivity extends AppCompatActivity {

    private TextView tv_command;
    private TextView tv_usage;
    private Button bt_grammar;
    private Button bt_option;
    private Button bt_example;
    private Button bt_next;
    private Button bt_break;

    private CommandDBEngine commandDBEngine;
    private UserDBEngine userDBEngine;
    private AdditionDBEngine additionDBEngine;


    //跳转到选择界面
    private void SelectAction(View view, int id) {

        Intent intent=new Intent(this, SelectActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //跳转到语法界面
    private void GrammarAction(View view,int id,int flag) {

        Intent intent=new Intent(this, GrammarActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    //跳转到选项界面
    private void OptionAction(View view,int id,int flag) {

        Intent intent=new Intent(this, OptionActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //跳转到实例界面
    private void ExampleAction(View view,int id,int flag) {

        Intent intent=new Intent(this, ExampleActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        userDBEngine=new UserDBEngine(this);
        commandDBEngine=new CommandDBEngine(this);
        additionDBEngine=new AdditionDBEngine(this);

        initView();
    }




    //初始化
    private void initView() {

        tv_command=findViewById(R.id.tv_command);
        tv_usage=findViewById(R.id.tv_usage);
        bt_grammar=findViewById(R.id.bt_grammar);
        bt_option=findViewById(R.id.bt_option);
        bt_example=findViewById(R.id.bt_example);
        bt_next=findViewById(R.id.bt_next);
        bt_break=findViewById(R.id.bt_break);

        //设置监听
        StudyActivity.MyOnClickListener l=new StudyActivity.MyOnClickListener();
        tv_command.setOnClickListener(l);
        tv_usage.setOnClickListener(l);
        bt_grammar.setOnClickListener(l);
        bt_option.setOnClickListener(l);
        bt_example.setOnClickListener(l);
        bt_next.setOnClickListener(l);
        bt_break.setOnClickListener(l);


        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        int flag=intent.getExtras().getInt("flag");

        if(flag==1){

            String tb_name=userDBEngine.query_tbnameByid(getWindow().getDecorView(),id);
            int now=userDBEngine.querynowByid(getWindow().getDecorView(),id,tb_name);
            commandDBEngine.open();
            int total=commandDBEngine.queryTotal(getWindow().getDecorView(),tb_name);

            if(now>total){
                now=total;
            }

            commandDBEngine.open();
            String a=commandDBEngine.queryCommandByNum(getWindow().getDecorView(),tb_name,now);
            String b=a.replace("\\n","\n");
            tv_command.setText(b);

            commandDBEngine.open();
            String c=commandDBEngine.queryUsageByNum(getWindow().getDecorView(),tb_name,now);
            String d=c.replace("\\n","\n");
            tv_usage.setText(d);

        }else if(flag==2){

            int now=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter8");
            int total=additionDBEngine.queryTotal(getWindow().getDecorView());

            if(now>total){
                now=total;
            }

            String a=additionDBEngine.queryCommand(getWindow().getDecorView(),now);
            String b=a.replace("\\n","\n");
            tv_command.setText(b);

            String c=additionDBEngine.queryUsage(getWindow().getDecorView(),now);
            String d=c.replace("\\n","\n");
            tv_usage.setText(d);


        }



    }


    private class MyOnClickListener implements View.OnClickListener{

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        int flag=intent.getExtras().getInt("flag");


        @Override
        public void onClick(View view) {
            switch (view.getId()){


                case R.id.bt_next:


                    if(flag==1){

                        String tb_name=userDBEngine.query_tbnameByid(getWindow().getDecorView(),id);
                        int now=userDBEngine.querynowByid(getWindow().getDecorView(),id,tb_name);

                        now++;

                        commandDBEngine.open();
                        int total=commandDBEngine.queryTotal(getWindow().getDecorView(),tb_name);

                        if(now>total){

                            now=total+1;
                            userDBEngine.updatenow(view,id,tb_name,now);
                            Toast.makeText(StudyActivity.this,"已完成本章学习，如果要继续学习，请返回后进行重置",Toast.LENGTH_SHORT).show();

                        }else{
                            userDBEngine.updatenow(view,id,tb_name,now);
                            commandDBEngine.open();
                            String a=commandDBEngine.queryCommandByNum(getWindow().getDecorView(),tb_name,now);
                            String b=a.replace("\\n","\n");
                            tv_command.setText(b);

                            commandDBEngine.open();
                            String c=commandDBEngine.queryUsageByNum(getWindow().getDecorView(),tb_name,now);
                            String d=c.replace("\\n","\n");
                            tv_usage.setText(d);
                        }

                    }else if(flag==2){

                        int now=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter8");
                        int total=additionDBEngine.queryTotal(getWindow().getDecorView());

                        now++;

                        if(now>total){

                            now=total+1;
                            userDBEngine.updatenow(view,id,"chapter8",now);
                            Toast.makeText(StudyActivity.this,"已完成本章学习，如果要继续学习，请返回后进行重置",Toast.LENGTH_SHORT).show();

                        }else{
                            userDBEngine.updatenow(view,id,"chapter8",now);

                            String a=additionDBEngine.queryCommand(getWindow().getDecorView(),now);
                            String b=a.replace("\\n","\n");
                            tv_command.setText(b);

                            String c=additionDBEngine.queryUsage(getWindow().getDecorView(),now);
                            String d=c.replace("\\n","\n");
                            tv_usage.setText(d);
                        }

                    }


                    break;


                case R.id.bt_grammar:

                    GrammarAction(view,id,flag);

                    break;


                case R.id.bt_option:

                    OptionAction(view,id,flag);

                    break;

                case R.id.bt_example:

                    ExampleAction(view,id,flag);

                    break;



                case R.id.bt_break:

                    SelectAction(view,id);

                    break;





            }
        }
    }









}
















