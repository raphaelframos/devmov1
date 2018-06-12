package com.raphaelframos.terceirao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.raphaelframos.terceirao.model.Disciplina;
import com.raphaelframos.terceirao.utils.GeralUtils;

import java.util.ArrayList;

public class DetalheNotaActivity extends AppCompatActivity {

    private static final Double VALOR_PRE_ESTABELECIDO = 200.0;
    private TextView textViewNomeProfessor;
    private TextView textViewNomeDisciplina;
    private TextView textViewMedia;
    private TextView textViewNota1;
    private TextView textViewNota2;
    private TextView textViewNota3;
    private TextView textViewNota4;
    private TextView textViewFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_nota);

        textViewMedia = findViewById(R.id.text_view_media);
        textViewNomeDisciplina = findViewById(R.id.text_view_detalhe_nome_disciplina);
        textViewNomeProfessor = findViewById(R.id.text_view_detalhe_nome_professor);
        textViewNota1 = findViewById(R.id.text_view_nota1);
        textViewNota2 = findViewById(R.id.text_view_nota2);
        textViewNota3 = findViewById(R.id.text_view_nota3);
        textViewNota4 = findViewById(R.id.text_view_nota4);
        textViewFeedback = findViewById(R.id.text_view_feedback);

        Disciplina disciplina = (Disciplina) getIntent().getSerializableExtra("disciplina");


        textViewNomeProfessor.setText(disciplina.getNomeDoProfessor());
        textViewNomeDisciplina.setText(disciplina.getNome());
        Double nota1 = disciplina.retornaNotaUm();
        Double nota2 = disciplina.retornaNotaDois();
        Double nota3 = disciplina.retornaNotaTres();
        Double nota4 = disciplina.retornaNotaQuatro();
        Double media = disciplina.calculaMedia();
        textViewNota1.setText(nota1.toString());
        textViewNota1.setTextColor(GeralUtils.getCorPeloValor(getApplicationContext(), nota1));

        textViewNota2.setText(nota2.toString());
        textViewNota2.setTextColor(GeralUtils.getCorPeloValor(getApplicationContext(), nota2));

        textViewNota3.setText(nota3.toString());
        textViewNota3.setTextColor(GeralUtils.getCorPeloValor(getApplicationContext(), nota3));
        textViewNota4.setText(nota4.toString());
        textViewNota4.setTextColor(GeralUtils.getCorPeloValor(getApplicationContext(), nota4));
        textViewMedia.setText(media.toString());
        textViewMedia.setTextColor(GeralUtils.getCorPeloValor(getApplicationContext(), media));

        Double feedback = calculaFeedback(disciplina.getNotas());
        if(feedback > 0){
            textViewFeedback.setText("Você precisa de " + feedback + " pontos!");
        }else{
            textViewFeedback.setText("Você está aprovado!");
        }



    }

    private Double calculaFeedback(ArrayList<Double> notas) {
        Double somatorio = 0.0;

        for(Double nota : notas){
            somatorio += nota;
        }

        return VALOR_PRE_ESTABELECIDO - somatorio;
    }
}
