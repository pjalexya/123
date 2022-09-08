package com.example.recite.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.recite.Helper.ReviewSqliteAssetHelper;

public class ReviewDBEngine {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;
    private static ReviewDBEngine instance;
    Cursor cursor=null;


    public ReviewDBEngine(Context context){
        this.sqLiteOpenHelper=new ReviewSqliteAssetHelper(context);
    }


    public static ReviewDBEngine getInstance(Context context){
        if(instance==null){
            instance=new ReviewDBEngine(context);
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


    //根据id查找命令
    public String queryCommandByid(View view, int id){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from review",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得command
                @SuppressLint("Range") String command1=cursor.getString(cursor.getColumnIndex("command"));

                //获得id
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("_id"));

                if (id1==id) {

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



    //根据命令查找用途
    public String queryUsageBycommand(View view,String command){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from review",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得command
                @SuppressLint("Range") String command1=cursor.getString(cursor.getColumnIndex("command"));

                //获得usage
                @SuppressLint("Range") String usage = cursor.getString(cursor.getColumnIndex("usage"));

                if (command1.equals(command)) {

                    cursor.close();
                    db.close();

                    return usage;
                }
            }
            cursor.close();
            db.close();
        }

        return null;

    }



    //根据命令查找语法
    public String queryGrammarBycommand(View view,String command){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from review",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得command
                @SuppressLint("Range") String command1=cursor.getString(cursor.getColumnIndex("command"));

                //获得grammar
                @SuppressLint("Range") String grammar = cursor.getString(cursor.getColumnIndex("grammar"));

                if (command1.equals(command)) {

                    cursor.close();
                    db.close();

                    return grammar;
                }
            }
            cursor.close();
            db.close();
        }

        return null;

    }


    //根据命令查找选项
    public String queryOptionBycommand(View view,String command){

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from review",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得command
                @SuppressLint("Range") String command1=cursor.getString(cursor.getColumnIndex("command"));

                //获得option
                @SuppressLint("Range") String option = cursor.getString(cursor.getColumnIndex("option"));

                if (command1.equals(command)) {

                    cursor.close();
                    db.close();

                    return option;
                }
            }
            cursor.close();
            db.close();
        }

        return null;

    }


    //根据命令查找选项
    public String queryExampleBycommand(View view,String command){


        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from review",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得command
                @SuppressLint("Range") String command1=cursor.getString(cursor.getColumnIndex("command"));

                //获得example
                @SuppressLint("Range") String example = cursor.getString(cursor.getColumnIndex("example"));

                if (command1.equals(command)) {

                    cursor.close();
                    db.close();

                    return example;
                }
            }
            cursor.close();
            db.close();
        }

        return null;

    }




}

