package com.example.recite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recite.Manager.AdditionDBEngine;
import com.example.recite.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCommandActivity extends AppCompatActivity {

    private EditText et_add;
    private Button bt_exit4;
    private Button bt_confirm;
    private AdditionDBEngine additionDBEngine;

    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);

        additionDBEngine=new AdditionDBEngine(this);

        requestPermission(this);

        initView();//初始化

    }


    public void MainAction(View view, int id) {//跳转到主界面
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startActivity(intent);

    }



    //初始化
    private void initView() {
        et_add=findViewById(R.id.et_add);
        bt_exit4=findViewById(R.id.bt_exit4);
        bt_confirm=findViewById(R.id.bt_confirm);

        //设置监听
        AddCommandActivity.MyOnClickListener l=new AddCommandActivity.MyOnClickListener();
        bt_exit4.setOnClickListener(l);
        bt_confirm.setOnClickListener(l);

    }


    private class MyOnClickListener implements View.OnClickListener{

        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id");//获取传递来的id



        @Override
        public void onClick(View view) {
            switch (view.getId()){


                case R.id.bt_exit4:

                   MainAction(view,id);

                    break;

                case R.id.bt_confirm:

                    String path=et_add.getText().toString().trim();
                        if(path.contains(".csv")){
                            readCsv(path);
                            Toast.makeText(AddCommandActivity.this,"文件读取成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddCommandActivity.this, "请检查文件是否为csv文件", Toast.LENGTH_SHORT).show();
                        }


            }
        }
    }



    private void readCsv(String s) {

        try {

            File csv = new File(s);   // CSV文件路径
            BufferedReader br = new BufferedReader(new FileReader(csv));

            br.readLine();
            String line = "";

            //读取csv文件
            while ((line = br.readLine()) != null) {

                /**
                 *  csv格式每一列内容以逗号分隔,因此要取出想要的内容,以逗号为分割符分割字符串即可,
                 *  把分割结果存到到数组中,根据数组来取得相应值
                 */
                String[] buffer = line.split(",");// 以逗号分隔


try{
additionDBEngine.insertCommand8(getWindow().getDecorView(),buffer[0],buffer[1],buffer[2],buffer[3],buffer[4]);
} catch (ArrayIndexOutOfBoundsException e) {

        }
                


            }
            br.close();
        }  catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    private void requestPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 判断有没有权限
            if (Environment.isExternalStorageManager()) {

            } else {
                Intent intent = new Intent( Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION );
                intent.setData( Uri.parse( "package:" + context.getPackageName() ) );
                startActivityForResult( intent,111 );
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 判断有没有权限
            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111 );
            }
        } else {

        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if (requestCode == 111) {
            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {

            } else {

            }
        }
    }

    


}