package com.example.recite.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdditionSqliteOpenHelper extends SQLiteOpenHelper {

    //对外提供函数，单例模式
    private static SQLiteOpenHelper Instance;
    public static synchronized SQLiteOpenHelper getInstance(Context context){
        if(Instance==null){
            Instance=new AdditionSqliteOpenHelper(context,"addition.db",null,1);
        }
        return Instance;
    }


    private AdditionSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table addition(_id integer primary key autoincrement,command text,usage text,grammar text,option text,example text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
