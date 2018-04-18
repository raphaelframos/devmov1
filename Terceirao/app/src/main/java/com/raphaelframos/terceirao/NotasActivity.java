package com.raphaelframos.terceirao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.raphaelframos.terceirao.adapter.NotaAdapter;
import com.raphaelframos.terceirao.banco_dados.BancoDeDados;
import com.raphaelframos.terceirao.model.Disciplina;

import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotas;
    private NotaAdapter notaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_materia, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onResume() {
        super.onResume();
     //   notaAdapter.atualiza(BancoDeDados.getInstance().getDisciplinas());
    }
}
