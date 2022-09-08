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

public class SelectActivity extends AppCompatActivity {

    private TextView tv_select1;
    private TextView tv_progress1;
    private Button bt_reset1;
    private Button bt_study1;

    private TextView tv_select2;
    private TextView tv_progress2;
    private Button bt_reset2;
    private Button bt_study2;

    private TextView tv_select3;
    private TextView tv_progress3;
    private Button bt_reset3;
    private Button bt_study3;

    private TextView tv_select4;
    private TextView tv_progress4;
    private Button bt_reset4;
    private Button bt_study4;

    private TextView tv_select5;
    private TextView tv_progress5;
    private Button bt_reset5;
    private Button bt_study5;

    private TextView tv_select6;
    private TextView tv_progress6;
    private Button bt_reset6;
    private Button bt_study6;

    private TextView tv_select7;
    private TextView tv_progress7;
    private Button bt_reset7;
    private Button bt_study7;

    private TextView tv_select8;
    private TextView tv_progress8;
    private Button bt_reset8;
    private Button bt_study8;

    private Button bt_del;
    private Button bt_break4;

    private UserDBEngine userDBEngine;
    private CommandDBEngine commandDBEngine;
    private AdditionDBEngine additionDBEngine;

    //跳转到学习界面
    public void StudyAction(View view, int id, int flag) {
        Intent intent=new Intent(this, StudyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //跳转到主界面
    public void MainAction(View view,int id) {
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        userDBEngine=new UserDBEngine(this);
        commandDBEngine=new CommandDBEngine(this);
        additionDBEngine=new AdditionDBEngine(this);

        initView();//初始化
    }



    //初始化
    private void initView() {

        tv_select1=findViewById(R.id.tv_select1);
        tv_progress1=findViewById(R.id.tv_progress1);
        bt_reset1=findViewById(R.id. bt_reset1);
        bt_study1=findViewById(R.id.bt_study1);

        tv_select2=findViewById(R.id.tv_select2);
        tv_progress2=findViewById(R.id.tv_progress2);
        bt_reset2=findViewById(R.id. bt_reset2);
        bt_study2=findViewById(R.id.bt_study2);

        tv_select3=findViewById(R.id.tv_select3);
        tv_progress3=findViewById(R.id.tv_progress3);
        bt_reset3=findViewById(R.id. bt_reset3);
        bt_study3=findViewById(R.id.bt_study3);

        tv_select4=findViewById(R.id.tv_select4);
        tv_progress4=findViewById(R.id.tv_progress4);
        bt_reset4=findViewById(R.id. bt_reset4);
        bt_study4=findViewById(R.id.bt_study4);

        tv_select5=findViewById(R.id.tv_select5);
        tv_progress5=findViewById(R.id.tv_progress5);
        bt_reset5=findViewById(R.id. bt_reset5);
        bt_study5=findViewById(R.id.bt_study5);

        tv_select6=findViewById(R.id.tv_select6);
        tv_progress6=findViewById(R.id.tv_progress6);
        bt_reset6=findViewById(R.id. bt_reset6);
        bt_study6=findViewById(R.id.bt_study6);

        tv_select7=findViewById(R.id.tv_select7);
        tv_progress7=findViewById(R.id.tv_progress7);
        bt_reset7=findViewById(R.id. bt_reset7);
        bt_study7=findViewById(R.id.bt_study7);

        tv_select8=findViewById(R.id.tv_select8);
        tv_progress8=findViewById(R.id.tv_progress8);
        bt_reset8=findViewById(R.id. bt_reset8);
        bt_study8=findViewById(R.id.bt_study8);

        bt_del=findViewById(R.id.bt_del);
        bt_break4=findViewById(R.id.bt_break4);

        //设置监听
        SelectActivity.MyOnClickListener l=new SelectActivity.MyOnClickListener();
        tv_select1.setOnClickListener(l);
        tv_progress1.setOnClickListener(l);
        bt_reset1.setOnClickListener(l);
        bt_study1.setOnClickListener(l);

        tv_select2.setOnClickListener(l);
        tv_progress2.setOnClickListener(l);
        bt_reset2.setOnClickListener(l);
        bt_study2.setOnClickListener(l);

        tv_select3.setOnClickListener(l);
        tv_progress3.setOnClickListener(l);
        bt_reset3.setOnClickListener(l);
        bt_study3.setOnClickListener(l);

        tv_select4.setOnClickListener(l);
        tv_progress4.setOnClickListener(l);
        bt_reset4.setOnClickListener(l);
        bt_study4.setOnClickListener(l);

        tv_select5.setOnClickListener(l);
        tv_progress5.setOnClickListener(l);
        bt_reset5.setOnClickListener(l);
        bt_study5.setOnClickListener(l);

        tv_select6.setOnClickListener(l);
        tv_progress6.setOnClickListener(l);
        bt_reset6.setOnClickListener(l);
        bt_study6.setOnClickListener(l);

        tv_select7.setOnClickListener(l);
        tv_progress7.setOnClickListener(l);
        bt_reset7.setOnClickListener(l);
        bt_study7.setOnClickListener(l);

        tv_select8.setOnClickListener(l);
        tv_progress8.setOnClickListener(l);
        bt_reset8.setOnClickListener(l);
        bt_study8.setOnClickListener(l);

        bt_break4.setOnClickListener(l);
        bt_del.setOnClickListener(l);

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id

        int finish1=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter1")-1;
        commandDBEngine.open();
        int total1=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter1");
        tv_progress1.setText("进度："+finish1+"/"+total1);

        int finish2=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter2")-1;
        commandDBEngine.open();
        int total2=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter2");
        tv_progress2.setText("进度："+finish2+"/"+total2);

        int finish3=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter3")-1;
        commandDBEngine.open();
        int total3=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter3");
        tv_progress3.setText("进度："+finish3+"/"+total3);

        int finish4=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter4")-1;
        commandDBEngine.open();
        int total4=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter4");
        tv_progress4.setText("进度："+finish4+"/"+total4);

        int finish5=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter5")-1;
        commandDBEngine.open();
        int total5=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter5");
        tv_progress5.setText("进度："+finish5+"/"+total5);

        int finish6=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter6")-1;
        commandDBEngine.open();
        int total6=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter6");
        tv_progress6.setText("进度："+finish6+"/"+total6);

        int finish7=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter7")-1;
        commandDBEngine.open();
        int total7=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter7");
        tv_progress7.setText("进度："+finish7+"/"+total7);

        int finish8=userDBEngine.querynowByid(getWindow().getDecorView(),id,"chapter8")-1;
        int total8=additionDBEngine.queryTotal(getWindow().getDecorView());
        tv_progress8.setText("进度："+finish8+"/"+total8);


    }



    private class MyOnClickListener implements View.OnClickListener{

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id


        @Override
        public void onClick(View view) {


            switch (view.getId()){

                case R.id.bt_study1:

                    userDBEngine.update_tbname(view,id,"chapter1");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset1:

                    userDBEngine.resetnow(view,id,"chapter1");
                    commandDBEngine.open();
                    int total1=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter1");
                    tv_progress1.setText("进度：0/"+total1);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;


                case R.id.bt_study2:

                    userDBEngine.update_tbname(view,id,"chapter2");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset2:

                    userDBEngine.resetnow(view,id,"chapter2");
                    commandDBEngine.open();
                    int total2=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter2");
                    tv_progress2.setText("进度：0/"+total2);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;


                case R.id.bt_study3:

                    userDBEngine.update_tbname(view,id,"chapter3");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset3:

                    userDBEngine.resetnow(view,id,"chapter3");
                    commandDBEngine.open();
                    int total3=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter3");
                    tv_progress3.setText("进度：0/"+total3);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.bt_study4:

                    userDBEngine.update_tbname(view,id,"chapter4");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset4:

                    userDBEngine.resetnow(view,id,"chapter4");
                    commandDBEngine.open();
                    int total4=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter4");
                    tv_progress4.setText("进度：0/"+total4);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.bt_study5:

                    userDBEngine.update_tbname(view,id,"chapter5");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset5:

                    userDBEngine.resetnow(view,id,"chapter5");
                    commandDBEngine.open();
                    int total5=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter5");
                    tv_progress5.setText("进度：0/"+total5);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.bt_study6:

                    userDBEngine.update_tbname(view,id,"chapter6");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset6:

                    userDBEngine.resetnow(view,id,"chapter6");
                    commandDBEngine.open();
                    int total6=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter6");
                    tv_progress6.setText("进度：0/"+total6);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.bt_study7:

                    userDBEngine.update_tbname(view,id,"chapter7");
                    StudyAction(view,id,1);

                    break;

                case R.id.bt_reset7:

                    userDBEngine.resetnow(view,id,"chapter7");
                    commandDBEngine.open();
                    int total7=commandDBEngine.queryTotal(getWindow().getDecorView(),"chapter7");
                    tv_progress7.setText("进度：0/"+total7);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.bt_study8:

                    int total=additionDBEngine.queryTotal(getWindow().getDecorView());
                    if(total>0){

                        StudyAction(view,id,2);
                    }else{

                        Toast.makeText(SelectActivity.this,"当前没有新添加的命令",Toast.LENGTH_SHORT).show();
                    }


                    break;

                case R.id.bt_reset8:

                    userDBEngine.resetnow(view,id,"chapter8");
                    int total8=additionDBEngine.queryTotal(getWindow().getDecorView());
                    tv_progress8.setText("进度：0/"+total8);
                    Toast.makeText(SelectActivity.this,"重置成功",Toast.LENGTH_SHORT).show();

                    break;

                case R.id.bt_del:

                    additionDBEngine.deleteCommand8(view);
                    userDBEngine.resetnow(view,id,"chapter8");
                    tv_progress8.setText("进度：0/0");
                    Toast.makeText(SelectActivity.this,"清空成功",Toast.LENGTH_SHORT).show();

                    break;


                case R.id.bt_break4:

                    MainAction(view,id);
                    break;

            }
        }
    }



















}