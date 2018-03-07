package com.raphaelframos.terceirao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroDeNotaActivity extends AppCompatActivity {

    private TextView textViewNomeDaDisciplina;
    private EditText editTextNotaUm;
    private EditText editTextNotaDois;
    private EditText editTextNotaTres;
    private EditText editTextNotaQuatro;
    private Button buttonSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_nota);

        textViewNomeDaDisciplina = findViewById(R.id.text_view_nome_disciplina);
        editTextNotaUm = findViewById(R.id.edit_text_nota_1);
        buttonSalvar = findViewById(R.id.button_salvar_nota);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String nota = editTextNotaUm.getText().toString();
                textViewNomeDaDisciplina.setText(nota);
            }
        });

    }
}
