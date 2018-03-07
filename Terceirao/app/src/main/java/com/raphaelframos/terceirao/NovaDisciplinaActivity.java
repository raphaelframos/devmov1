package com.raphaelframos.terceirao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovaDisciplinaActivity extends AppCompatActivity {

    private EditText editNome;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_disciplina);

        String nomeDoAluno = getIntent().getStringExtra("nome");
        int idade = getIntent().getIntExtra("idade", 0);
        Toast.makeText(getApplicationContext(), "O nome do aluno é : " + nomeDoAluno + " com idade de " + idade, Toast.LENGTH_LONG).show();

        editNome = findViewById(R.id.edit_text_nome_disciplina);
        buttonSalvar = findViewById(R.id.button_salvar);
    }
}
