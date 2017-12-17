package com.jonat.callblocker.database.conection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jonat.callblocker.database.entities.Telefone;

/**
 * Created by jonat on 15/12/2017.
 */

public class DataBaseHelper<E> extends OrmLiteSqliteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "sistema.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource src) {
        try{
            TableUtils.createTable(src, Telefone.class);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource src, int i, int i1) {
        try{
            TableUtils.dropTable(src, Telefone.class, true);
            onCreate(db, src);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
