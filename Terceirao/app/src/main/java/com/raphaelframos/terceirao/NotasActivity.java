package com.raphaelframos.terceirao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.raphaelframos.terceirao.adapter.NotaAdapter;
import com.raphaelframos.terceirao.model.Disciplina;

import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        recyclerViewNotas = findViewById(R.id.recycler_notas);
        recyclerViewNotas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(new Disciplina("Matemática", 50));
        disciplinas.add(new Disciplina("Ciências", 60));
        disciplinas.add(new Disciplina("História", 100));
        disciplinas.add(new Disciplina("Geografia", 90));

        recyclerViewNotas.setAdapter(new NotaAdapter(disciplinas));
    }
}
