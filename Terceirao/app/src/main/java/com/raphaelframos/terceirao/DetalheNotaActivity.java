package com.raphaelframos.terceirao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.raphaelframos.terceirao.model.Disciplina;

public class DetalheNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_nota);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Portugues");
        disciplina.setNomeDoProfessor("Renato");
        disciplina.adicionaNota(50);
        disciplina.adicionaNota(60);
        disciplina.adicionaNota(50);
        disciplina.adicionaNota(60);

        disciplina.calculaMedia();

    }
}
