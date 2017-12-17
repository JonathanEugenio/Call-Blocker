package com.jonat.callblocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jonat.callblocker.database.dao.TelefoneDAO;
import com.jonat.callblocker.database.entities.Telefone;

public class InserirActivity extends AppCompatActivity {

    private EditText valor;
    private Button btnInsert, btnBack;
    private TelefoneDAO dao;
    private Telefone telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        valor = (EditText) findViewById(R.id.valor);

        dao = new TelefoneDAO(getApplicationContext());

        telefone = new Telefone();

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valor.getText().toString()== "" || valor.length() != 12){

                    Toast.makeText(InserirActivity.this, "Falha: Número incorreto",
                            Toast.LENGTH_SHORT).show();
                    valor.setText("");
                }else if(TelefoneDAO.getInstance(getApplicationContext())
                        .hasTelephone(valor.getText().toString())){
                    Toast.makeText(InserirActivity.this, "Falha: Número já cadastrado",
                            Toast.LENGTH_SHORT).show();
                    valor.setText("");
                }else{
                    telefone.setNumero(valor.getText().toString());

                    dao.insert(telefone);

                    Toast.makeText(InserirActivity.this, "Número inserido com sucesso",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InserirActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}