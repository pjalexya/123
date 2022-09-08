package com.example.recite.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.recite.Helper.UserSqliteOpenHelper;

public class UserDBEngine {


    public UserDBEngine(Context context) {

        SQLiteOpenHelper userSqliteOpenHelper = UserSqliteOpenHelper.getInstance(context);
    }


    //创建数据库
    public void createDB(View view) {

        SQLiteOpenHelper helper = UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
    }


    //创建用户
    public void insertUser(View view, String name, String pwd, int chapter1,int chapter2,int chapter3,int chapter4,int chapter5,int chapter6,int chapter7,int chapter8,int level,String tb_name,int rn) {

        SQLiteOpenHelper helper = UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db.isOpen()) {

            db.execSQL("insert into user(name, pwd,chapter1,chapter2,chapter3,chapter4,chapter5,chapter6,chapter7,chapter8,level,tb_name,rn) values(?, ?, ?, ?,?,?,?,?,?,?,?,?,?)", new Object[]{name, pwd, chapter1,chapter2,chapter3,chapter4,chapter5,chapter6,chapter7,chapter8,level,tb_name,rn});

        }

        db.close();
    }


    //根据用户名查找用户id
    public int queryidByname(View view,String name) {

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){

            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得ID
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_id"));

                //获得用户名
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("name"));

                if (username.equals(name)) {//找到了该用户
                    cursor.close();
                    db.close();

                    return id;

                }
            }
            cursor.close();
            db.close();
        }

        return 0;
    }


    //根据用户名查找密码
    public String querypwdByname(View view,String name) {

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得密码
                @SuppressLint("Range") String userpwd=cursor.getString(cursor.getColumnIndex("pwd"));

                //获得用户名
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("name"));

                if (username.equals(name)) {

                    cursor.close();
                    db.close();

                    return userpwd;
                }
            }
            cursor.close();
            db.close();
        }

        return null;
    }

    //根据id查找用户名
    public String querynameByid(View view,int id) {

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得ID
                @SuppressLint("Range") int userid=cursor.getInt(cursor.getColumnIndex("_id"));

                //获得用户名
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("name"));

                if (userid==id) {

                    cursor.close();
                    db.close();

                    return username;
                }
            }
            cursor.close();
            db.close();
        }

        return null;
    }


    //根据id查找章节学习进度数
    public int querynowByid(View view,int id,String chapter){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得ID
                @SuppressLint("Range") int userid=cursor.getInt(cursor.getColumnIndex("_id"));

                //获得章节数
                @SuppressLint("Range") int now= cursor.getInt(cursor.getColumnIndex(chapter));

                if (userid==id) {

                    cursor.close();
                    db.close();

                    return now;
                }
            }
            cursor.close();
            db.close();
        }

        return 0;

    }


    //根据id查找复习阶段数
    public int querylevelByid(View view,int id){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得ID
                @SuppressLint("Range") int userid=cursor.getInt(cursor.getColumnIndex("_id"));

                //获得复习阶段数
                @SuppressLint("Range") int level1= cursor.getInt(cursor.getColumnIndex("level"));

                if (userid==id) {

                    cursor.close();
                    db.close();

                    return level1;
                }
            }
            cursor.close();
            db.close();
        }

        return -1;

    }


    //学习完成数更新
    public void updatenow(View view,int id,String chapter,int now){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();


        if(db.isOpen()){

            db.execSQL("update user set "+chapter+"="+now+" where _id=?",new Object[]{id});

        }

        db.close();

    }


    //根据id查找当前学习章节数据表名
    public String query_tbnameByid(View view,int id){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得ID
                @SuppressLint("Range") int userid=cursor.getInt(cursor.getColumnIndex("_id"));

                //获得当前学习章节数据表名
                @SuppressLint("Range") String tb_name1= cursor.getString(cursor.getColumnIndex("tb_name"));

                if (userid==id) {

                    cursor.close();
                    db.close();

                    return tb_name1;
                }
            }
            cursor.close();
            db.close();
        }

        return null;

    }


    //当前学习章节数据表名更新
    public void update_tbname(View view,int id,String tb_name){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();


        if(db.isOpen()){

            db.execSQL("update user set tb_name=? where _id=?",new Object[]{tb_name,id});

        }

        db.close();

    }


    //学习完成数重置
    public void resetnow(View view,int id,String chapter){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();


        if(db.isOpen()){

            db.execSQL("update user set "+chapter+"=1 where _id=?",new Object[]{id});

        }

        db.close();

    }



    //进入下一阶段
    public void nextlevel(View view,int id){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();


        if(db.isOpen()){

            db.execSQL("update user set level=level+1 where _id=?",new Object[]{id});

        }

        db.close();


    }


    public int queryRnByid(View view,int id) {

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen()){

            Cursor cursor=db.rawQuery("select * from user ",null);

            //遍历游标
            while(cursor.moveToNext()) {

                //获得ID
                @SuppressLint("Range") int id1 = cursor.getInt(cursor.getColumnIndex("_id"));

                //获得rn
                @SuppressLint("Range") int rn= cursor.getInt(cursor.getColumnIndex("rn"));

                if (id1==id) {

                    cursor.close();
                    db.close();
                    return rn;

                }
            }
            cursor.close();
            db.close();
        }

        return 0;
    }



    public void  Rn(View view,int id,int t){

        SQLiteOpenHelper helper=UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();


        if(db.isOpen()){

            db.execSQL("update user set rn="+t+" where _id=?",new Object[]{id});

        }

        db.close();

    }


    public void  Initlevel(View view,int id){

        SQLiteOpenHelper helper= UserSqliteOpenHelper.getInstance(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();


        if(db.isOpen()){

            db.execSQL("update user set level=1 where _id=?",new Object[]{id});

        }

        db.close();

    }





















}
