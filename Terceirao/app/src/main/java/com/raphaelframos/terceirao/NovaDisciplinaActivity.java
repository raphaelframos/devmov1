package com.raphaelframos.terceirao;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.raphaelframos.terceirao.banco_dados.BancoDeDados;
import com.raphaelframos.terceirao.model.Disciplina;
import com.raphaelframos.terceirao.utils.GeralUtils;

public class NovaDisciplinaActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editTextNomeProfessor;
    private Button buttonSalvar;
    private EditText editTextNota1;
    private EditText editTextNota2;
    private EditText editTextNota3;
    private EditText editTextNota4;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_disciplina);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        disciplina = (Disciplina) getIntent().getSerializableExtra("disciplina");


        editNome = findViewById(R.id.edit_text_nome_disciplina);
        editTextNomeProfessor = findViewById(R.id.edit_text_nome_professor);
        editTextNota1 = findViewById(R.id.edit_text_nota1);
        editTextNota2 = findViewById(R.id.edit_text_nota2);
        editTextNota3 = findViewById(R.id.edit_text_nota3);
        editTextNota4 = findViewById(R.id.edit_text_nota4);

        buttonSalvar = findViewById(R.id.button_salvar);

        if(disciplina != null){
            editNome.setText(disciplina.getNome());
        }

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(capturaDados()){

                    SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_config), Context.MODE_PRIVATE);
                    String id = sharedPreferences.getString(getString(R.string.id), "");

                    DatabaseReference myRef = database.getReference("disciplinas");
                    myRef.child(id).push().setValue(disciplina).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(NovaDisciplinaActivity.this);
                            alerta.setTitle("Nova disciplina");
                            alerta.setMessage("Disciplina adicionada com sucesso!");
                            alerta.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                            alerta.show();
                        }
                    });

                }
            }
        });
    }

    public boolean capturaDados(){
        String nomeDisciplina = editNome.getText().toString();
        String nomeProfessor = editTextNomeProfessor.getText().toString();
        String nota1 = editTextNota1.getText().toString();
        String nota2 = editTextNota2.getText().toString();
        String nota3 = editTextNota3.getText().toString();
        String nota4 = editTextNota4.getText().toString();

        if(nomeDisciplina.isEmpty()){
            editNome.setError("Campo em branco!");
            return false;
        }

        disciplina = new Disciplina();
        disciplina.setNome(nomeDisciplina);
        disciplina.setNomeDoProfessor(nomeProfessor);
        disciplina.adicionaNota(nota1);
        disciplina.adicionaNota(nota2);
        disciplina.adicionaNota(nota3);
        disciplina.adicionaNota(nota4);

        return true;
    }
}
