package com.jonat.callblocker.database.dao;

import android.content.Context;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jonat.callblocker.database.entities.Telefone;

import java.util.ArrayList;


public class TelefoneDAO extends GenericDao<Telefone> {

    private static TelefoneDAO mInstance;


    public static TelefoneDAO getInstance(Context context){
        if(mInstance == null){
            mInstance = new TelefoneDAO(context);
        }
        return mInstance;
    }


    public TelefoneDAO(Context context) {

        super(context, Telefone.class);
    }

    public boolean hasTelephone(String num){
        ArrayList<Telefone> tel = new ArrayList<>();
        try{
            QueryBuilder<Telefone, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq("Numero",num);
            tel = (ArrayList<Telefone>) dao.query(queryBuilder.prepare());
        }catch (Exception e){
            e.printStackTrace();
        }
        return (tel.size() > 0);
    }


    public void delete(String num) {
        try{
            DeleteBuilder<Telefone, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("Numero", num);
            dao.delete(deleteBuilder.prepare());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
