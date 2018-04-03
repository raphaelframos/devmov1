package com.raphaelframos.terceirao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raphaelframos.terceirao.banco_dados.BancoDeDados;
import com.raphaelframos.terceirao.model.Disciplina;

public class NovaDisciplinaActivity extends AppCompatActivity {

    private EditText editNome;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_disciplina);

        String nomeDoAluno = getIntent().getStringExtra("nome");
        int idade = getIntent().getIntExtra("idade", 0);
        Disciplina disciplina = (Disciplina) getIntent().getSerializableExtra("disciplina");


        editNome = findViewById(R.id.edit_text_nome_disciplina);
        buttonSalvar = findViewById(R.id.button_salvar);

        if(disciplina != null){
            editNome.setText(disciplina.getNome());
        }

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeDaDisciplina = editNome.getText().toString();
                BancoDeDados.getInstance().salva(nomeDaDisciplina);
                Toast.makeText(getApplicationContext(), "Quantidade de nomes " + BancoDeDados.getInstance().getDisciplinas().size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
