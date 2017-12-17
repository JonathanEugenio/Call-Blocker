package com.jonat.callblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jonat.callblocker.database.dao.TelefoneDAO;
import com.jonat.callblocker.database.entities.Telefone;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TelefoneModel> telefoneModels;
    private RecyclerAdapter mAdapter;
    private ArrayList<Telefone> mTelefones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        mTelefones = (ArrayList<Telefone>) TelefoneDAO.getInstance(this).getAll();


        if(mTelefones.size() == 0){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.vazio);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();

                }
            });

            builder.create().show();
        }else{

            telefoneModels = new ArrayList<>();

            for(Telefone t: mTelefones){
                TelefoneModel telefoneModel = new TelefoneModel();
                telefoneModel.setNumero(t.getNumero());
                telefoneModels.add(telefoneModel);
            }

            mAdapter = new RecyclerAdapter(this, telefoneModels);

            recyclerView = (RecyclerView) findViewById(R.id.item_list);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
    }
}
