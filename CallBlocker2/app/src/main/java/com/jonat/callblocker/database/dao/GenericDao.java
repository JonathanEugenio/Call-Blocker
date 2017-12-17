package com.jonat.callblocker.database.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.jonat.callblocker.database.conection.DataBaseHelper;
import com.jonat.callblocker.database.entities.Telefone;

import java.util.List;

/**
 * Created by jonat on 15/12/2017.
 */

public abstract class GenericDao<E> extends DataBaseHelper<E> {

    protected Dao<E, Integer> dao;
    private Class<E> type;

    public GenericDao(Context context, Class<E> type) {
        super(context);
        this.type = type;
        setDao();
    }

    protected void setDao() {
        try{
            dao = DaoManager.createDao(getConnectionSource(), type);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<E> getAll() {
        try{
            List<E> result = dao.queryForAll();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void insert(E obj) {
        try{
            dao.create(obj);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}