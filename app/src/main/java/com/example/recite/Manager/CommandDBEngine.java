package com.example.recite.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.recite.Helper.CommandSqliteAssetHelper;

public class CommandDBEngine {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;
    private static CommandDBEngine instance;
    Cursor cursor=null;


    public CommandDBEngine(Context context){
        this.sqLiteOpenHelper=new CommandSqliteAssetHelper(context);
    }


    public static CommandDBEngine getInstance(Context context){
        if(instance==null){
            instance=new CommandDBEngine(context);
        }

        return instance;
    }


    public void open(){
        this.db=sqLiteOpenHelper.getWritableDatabase();
    }


    public void close(){
        if(db!=null){
            this.db.close();
        }
    }



    //根据序号查找命令
    public String queryCommandByNum(View view, String TABLE_NAME, int num){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得command
                @SuppressLint("Range") String command1=cursor.getString(cursor.getColumnIndex("command"));

                //获得num
                @SuppressLint("Range") int num1 = cursor.getInt(cursor.getColumnIndex("num"));

                if (num1==num) {

                    cursor.close();
                    db.close();

                    return command1;
                }
            }
            cursor.close();
            db.close();
        }

        return null;


    }


    //根据序号查找用途
    public String queryUsageByNum(View view, String TABLE_NAME,int num){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得usage
                @SuppressLint("Range") String usage1=cursor.getString(cursor.getColumnIndex("usage"));

                //获得num
                @SuppressLint("Range") int num1 = cursor.getInt(cursor.getColumnIndex("num"));

                if (num1==num) {

                    cursor.close();
                    db.close();

                    return usage1;
                }
            }
            cursor.close();
            db.close();
        }

        return null;


    }


    //根据序号查找语法
    public String queryGrammarByNum(View view, String TABLE_NAME,int num){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得grammar
                @SuppressLint("Range") String grammar1=cursor.getString(cursor.getColumnIndex("grammar"));

                //获得num
                @SuppressLint("Range") int num1 = cursor.getInt(cursor.getColumnIndex("num"));

                if (num1==num) {

                    cursor.close();
                    db.close();

                    return grammar1;
                }
            }
            cursor.close();
            db.close();
        }

        return null;


    }


    //根据序号查找选项
    public String queryOptionByNum(View view, String TABLE_NAME,int num){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得option
                @SuppressLint("Range") String option1=cursor.getString(cursor.getColumnIndex("option"));

                //获得num
                @SuppressLint("Range") int num1 = cursor.getInt(cursor.getColumnIndex("num"));

                if (num1==num) {

                    cursor.close();
                    db.close();

                    return option1;
                }
            }
            cursor.close();
            db.close();
        }

        return null;


    }


    //根据序号查找实例
    public String queryExampleByNum(View view, String TABLE_NAME,int num){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得example
                @SuppressLint("Range") String example1=cursor.getString(cursor.getColumnIndex("example"));

                //获得num
                @SuppressLint("Range") int num1 = cursor.getInt(cursor.getColumnIndex("num"));

                if (num1==num) {

                    cursor.close();
                    db.close();

                    return example1;
                }
            }
            cursor.close();
            db.close();
        }

        return null;


    }


    //查询命令表中命令总数
    public int queryTotal(View view,String TABLE_NAME){

        int t=0;

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);

            //遍历游标
            while(cursor.moveToNext()) {
                t++;
            }

            cursor.close();
            db.close();
        }

        return t;

    }





}
