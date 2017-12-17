package com.jonat.callblocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jonat.callblocker.database.dao.TelefoneDAO;
import com.jonat.callblocker.database.entities.Telefone;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inserirtela(View view){

        Intent intent = new Intent(this, InserirActivity.class);

        startActivity(intent);
    }

    public void listartela(View view){

        Intent intent = new Intent(this, ListarActivity.class);

        startActivity(intent);
    }
}
