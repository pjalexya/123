package com.example.recite.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSqliteOpenHelper extends SQLiteOpenHelper {

    //对外提供函数，单例模式
    private static SQLiteOpenHelper Instance;
    public static synchronized SQLiteOpenHelper getInstance(Context context){
        if(Instance==null){
            Instance=new UserSqliteOpenHelper(context,"user.db",null,1);
        }
        return Instance;
    }


    private UserSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(_id integer primary key autoincrement,name text,pwd text,chapter1 int,chapter2 int,chapter3 int,chapter4 int,chapter5 int,chapter6 int,chapter7 int,chapter8 int,level int,tb_name String,rn int)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

