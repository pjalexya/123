package com.example.recite.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.recite.Helper.AdditionSqliteOpenHelper;

public class AdditionDBEngine {

    public AdditionDBEngine(Context context) {

        SQLiteOpenHelper addtionSqliteOpenHelper = AdditionSqliteOpenHelper.getInstance(context);
    }


    //创建数据库
    public void createDB(View view) {

        SQLiteOpenHelper helper = AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
    }


    //添加命令
    public void insertCommand8(View view, String command, String usage, String grammar,String option,String example) {

        SQLiteOpenHelper helper = AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db.isOpen()) {

            db.execSQL("insert into addition(command,usage,grammar,option,example) values(?, ?, ?, ?,?)", new Object[]{command,usage,grammar,option,example});

        }

        db.close();

    }


    //清空命令表
    public void deleteCommand8(View view){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();

        if(db.isOpen()){

            db.execSQL("delete from addition where _id>0");

            db.execSQL("update sqlite_sequence set seq=0 where name='addition'");

        }

        db.close();

    }


    //查询命令表中命令总数
    public int queryTotal(View view){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        int t=0;

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from addition",null);

            //遍历游标
            while(cursor.moveToNext()) {
                t++;
            }

            cursor.close();
            db.close();
        }

        return t;

    }



    //根据id查找命令
    public String queryCommand(View view,int id){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from addition",null);

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


    //根据id查找用途
    public String queryUsage(View view,int id){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from addition",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得usage
                @SuppressLint("Range") String usage1=cursor.getString(cursor.getColumnIndex("usage"));

                //获得id
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("_id"));

                if (id1==id) {

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


    //根据id查找语法
    public String queryGrammar(View view,int id){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from addition",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得grammar
                @SuppressLint("Range") String grammar1=cursor.getString(cursor.getColumnIndex("grammar"));

                //获得id
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("_id"));

                if (id1==id) {

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


    //根据id查找选项
    public String queryOption(View view,int id){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from addition",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得option
                @SuppressLint("Range") String option1=cursor.getString(cursor.getColumnIndex("option"));

                //获得id
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("_id"));

                if (id1==id) {

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


    //根据id查找实例
    public String queryExample(View view,int id){

        SQLiteOpenHelper helper=AdditionSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from addition",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得example
                @SuppressLint("Range") String example1=cursor.getString(cursor.getColumnIndex("example"));

                //获得id
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("_id"));

                if (id1==id) {

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



}

