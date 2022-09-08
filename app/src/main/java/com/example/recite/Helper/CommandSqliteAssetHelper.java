package com.example.recite.Helper;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CommandSqliteAssetHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME="command.db";
    private static final int DATABASE_VERSION=1;

    public CommandSqliteAssetHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

}
