package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recite.Manager.ReviewDBEngine;
import com.example.recite.R;

import com.example.recite.Manager.UserDBEngine;

public class ReviewActivity extends AppCompatActivity {

    private TextView tv_command1;
    private TextView tv_usage1;
    private Button bt_grammar1;
    private Button bt_option1;
    private Button bt_example1;
    private Button bt_next1;
    private Button bt_break11;

    private UserDBEngine userDBEngine;
    private ReviewDBEngine reviewDBEngine;

    static String a[]= new String[100];

    //跳转到主界面
    public void MainAction(View view,int id) {
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //跳转到语法界面
    private void GrammarAction(View view, int id, String command,int flag) {

        Intent intent=new Intent(this, GrammarActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putString("command",command);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    //跳转到选项界面
    private void OptionAction(View view,int id,String command,int flag) {

        Intent intent=new Intent(this, OptionActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putString("command",command);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //跳转到实例界面
    private void ExampleAction(View view,int id,String command,int flag) {

        Intent intent=new Intent(this, ExampleActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putString("command",command);
        bundle.putInt("flag",flag);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        userDBEngine=new UserDBEngine(this);
        reviewDBEngine=new ReviewDBEngine(this);

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        int level=userDBEngine.querylevelByid(getWindow().getDecorView(),id);

        Assign(getWindow().getDecorView(),a,level);

        initView();


    }


    //初始化
    private void initView() {

        tv_command1=findViewById(R.id.tv_command1);
        tv_usage1=findViewById(R.id.tv_usage1);
        bt_grammar1=findViewById(R.id.bt_grammar1);
        bt_option1=findViewById(R.id.bt_option1);
        bt_example1=findViewById(R.id.bt_example1);
        bt_next1=findViewById(R.id.bt_next1);
        bt_break11=findViewById(R.id.bt_break11);

        //设置监听
        ReviewActivity.MyOnClickListener l=new ReviewActivity.MyOnClickListener();
        tv_command1.setOnClickListener(l);
        tv_usage1.setOnClickListener(l);
        bt_grammar1.setOnClickListener(l);
        bt_option1.setOnClickListener(l);
        bt_example1.setOnClickListener(l);
        bt_next1.setOnClickListener(l);
        bt_break11.setOnClickListener(l);


        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id
        int level=userDBEngine.querylevelByid(getWindow().getDecorView(),id);
        int t=size(level);
        int c=userDBEngine.queryRnByid(getWindow().getDecorView(),id);

        if(c>=t){
            c=t-1;
        }
        tv_command1.setText(a[c]);
        reviewDBEngine.open();
        String usage1=reviewDBEngine.queryUsageBycommand(getWindow().getDecorView(),a[c]);
        tv_usage1.setText(usage1);

    }


    private class MyOnClickListener implements View.OnClickListener{

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id


        @Override
        public void onClick(View view) {

            int level=userDBEngine.querylevelByid(view,id);
            int t=size(level);
            int c=userDBEngine.queryRnByid(view,id);

            switch (view.getId()){


                case R.id.bt_next1:

                    c++;
                    userDBEngine.Rn(view,id,c);
                    if(c<t){

                        tv_command1.setText(a[c]);
                        reviewDBEngine.open();
                        String usage1=reviewDBEngine.queryUsageBycommand(view,a[c]);
                        tv_usage1.setText(usage1);

                    }else{

                        userDBEngine.nextlevel(view,id);
                        userDBEngine.Rn(view,id,0);
                        Toast.makeText(ReviewActivity.this,"已完成本阶段的学习,自动返回主页",Toast.LENGTH_SHORT).show();
                        MainAction(view,id);
                    }



                    break;


                case R.id.bt_grammar1:

                    GrammarAction(view,id,a[c],3);

                    break;


                case R.id.bt_option1:

                    OptionAction(view,id,a[c],3);

                    break;

                case R.id.bt_example1:

                    ExampleAction(view,id,a[c],3);

                    break;



                case R.id.bt_break11:

                    MainAction(view,id);

                    break;





            }
        }
    }



    public int size(int m){

        if(m==1||m==14||(m>=16&&m<=19)){

            return 16;
        }

        if(m==2||m==3||m==13||m==15){

            return 24;
        }

        if(m>=4&&m<=7){

            return 32;
        }

        if(m>=8&&m<=12){

            return 40;
        }

        if(m>=20&&m<=38){

            return 8;
        }


        return m;
    }


    public void Assign(View view, String a[], int m){//m为当前所处阶段数

        ReviewDBEngine reviewDBEngine=ReviewDBEngine.getInstance(view.getContext());
        reviewDBEngine.open();

        int i;
        int j;
        int count;

        if(m>41){

        }

        else {
            switch (m) {

                case 1://list1 *list1

                    count = 1;

                    for (i = 0; i < 8; i++)//学习list1
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 1;//计数器重置为1

                    for (i = 8; i <16; i++)//复习list1
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;


                case 2://list2 *list1→2

                    count = 9;

                    for (i = 0; i < 8; i++)//学习list2
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 1;//计数器重置为1

                    for (i = 8; i <24; i++)//复习list1、2
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;


                case 3://list3 *list2、3

                    count = 17;

                    for (i = 0; i < 8; i++)//学习list3
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 9;

                    for (i = 8; i <24; i++)//复习list2、3
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 4://list4 *list1、3、4

                    count = 25;

                    for (i = 0; i < 8; i++)//学习list4
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 1;//计数器重置为1

                    for (i = 8; i <16; i++)//复习list1
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=17;

                    for (i = 16; i <32; i++)//复习list3、4
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 5://list5 *list2、4、5

                    count = 33;

                    for (i = 0; i < 8; i++)//学习list5
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 9;

                    for (i = 8; i <16; i++)//复习list2
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=25;

                    for (i = 16; i <32; i++)//复习list4、5
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 6://list6 *list3、5、6

                    count = 41;

                    for (i = 0; i < 8; i++)//学习list6
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 17;

                    for (i = 8; i <16; i++)//复习list3
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=33;

                    for (i = 16; i <32; i++)//复习list5、6
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;


                case 7:// list7 *lis4、6、7

                    count = 49;

                    for (i = 0; i < 8; i++)//学习list7
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 25;

                    for (i = 8; i <16; i++)//复习list4
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=41;

                    for (i = 16; i <32; i++)//复习list6、7
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;


                case 8://list8 *list1、5、7、8

                    count = 57;

                    for (i = 0; i < 8; i++)//学习list8
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 1;

                    for (i = 8; i <16; i++)//复习list1
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 33;

                    for (i = 16; i <24; i++)//复习list5
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=49;

                    for (i = 24; i <40; i++)//复习list7、8
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;


                case 9:// list9 *list2、6、8、9

                    count = 65;

                    for (i = 0; i < 8; i++)//学习list9
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 9;

                    for (i = 8; i <16; i++)//复习list2
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 41;

                    for (i = 16; i <24; i++)//复习list6
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=57;

                    for (i = 24; i <40; i++)//复习list8、9
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }
                    break;


                case 10://list10 *list3、7、9、10

                    count = 73;

                    for (i = 0; i < 8; i++)//学习list10
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 17;

                    for (i = 8; i <16; i++)//复习list3
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 49;

                    for (i = 16; i <24; i++)//复习list7
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=65;

                    for (i = 24; i <40; i++)//复习list9、10
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 11://list11 *list4、8、10、11

                    count = 81;

                    for (i = 0; i < 8; i++)//学习list11
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 25;

                    for (i = 8; i <16; i++)//复习list4
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 57;

                    for (i = 16; i <24; i++)//复习list8
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=73;

                    for (i = 24; i <40; i++)//复习list10、11
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 12://list12 *list5、9、11、12

                    count = 89;

                    for (i = 0; i < 8; i++)//学习list12
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 33;

                    for (i = 8; i <16; i++)//复习list5
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 65;

                    for (i = 16; i <24; i++)//复习list9
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count=81;

                    for (i = 24; i <40; i++)//复习list11、12
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;

                case 13://*list6、10、12

                    count = 41;

                    for (i = 0; i < 8; i++)//复习list6
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 73;

                    for (i = 8; i <16; i++)//复习list10
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 89;

                    for (i = 16; i <24; i++)//复习list12
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 14://*list7、11

                    count = 49;

                    for (i = 0; i < 8; i++)//复习list7
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 81;

                    for (i = 8; i <16; i++)//复习list11
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;

                case 15://*list1、8、12

                    count = 1;

                    for (i = 0; i < 8; i++)//复习list1
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 57;

                    for (i = 8; i <16; i++)//复习list8
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 89;

                    for (i = 16; i <24; i++)//复习list12
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;

                case 16:// *list2、9

                    count = 9;

                    for (i = 0; i < 8; i++)//复习list2
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 65;

                    for (i = 8; i <16; i++)//复习list9
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }


                    break;


                case 17://*list3、10

                    count = 17;

                    for (i = 0; i < 8; i++)//复习list3
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 73;

                    for (i = 8; i <16; i++)//复习list10
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }
                    break;


                case 18://*list4、11

                    count = 25;

                    for (i = 0; i < 8; i++)//复习list4
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 81;

                    for (i = 8; i <16; i++)//复习list11
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 19://*list5、12

                    count = 33;

                    for (i = 0; i < 8; i++)//复习list5
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    count = 89;

                    for (i = 8; i <16; i++)//复习list12
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 20:// *list6

                case 32:

                    count = 41;

                    for (i = 0; i < 8; i++)//复习list6
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 21://*list7

                case 33:

                    count = 49;

                    for (i = 0; i < 8; i++)//复习list7
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 22://*list8

                case 34:

                    count = 57;

                    for (i = 0; i < 8; i++)//复习list8
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 23://*list9

                case 35:

                    count = 65;

                    for (i = 0; i < 8; i++)//复习list9
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 24:// *list10

                case 36:

                    count = 73;

                    for (i = 0; i < 8; i++)//复习list10
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;


                case 25://*list11

                case 37:

                    count = 81;

                    for (i = 0; i < 8; i++)//复习list11
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 26://*list12

                case 38:

                    count = 89;

                    for (i = 0; i < 8; i++)//复习list12
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 27://*list1

                    count = 1;

                    for (i = 0; i < 8; i++)//学习list1
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }
                    break;

                case 28://*list2

                    count = 9;

                    for (i = 0; i < 8; i++)//学习list2
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }
                    break;

                case 29://*list3

                    count = 17;

                    for (i = 0; i < 8; i++)//学习list3
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 30://*list4

                    count = 25;

                    for (i = 0; i < 8; i++)//学习list4
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;

                case 31://*list5

                    count = 33;

                    for (i = 0; i < 8; i++)//学习list5
                    {
                        reviewDBEngine.open();
                        a[i]= reviewDBEngine.queryCommandByid(view, count);
                        count++;

                    }

                    break;



            }
        }

    }








}