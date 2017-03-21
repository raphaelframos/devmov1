package com.powellapps.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        double resultadoDoCalculo = getIntent().getDoubleExtra("resultadoDoCalculo", 0);
        calculaResultado(resultadoDoCalculo);
    }













    public void calculaResultado(double resultadoDoCalculo){
        if(resultadoDoCalculo <= 18.5){
            //Abaixo do peso ideal
            mostraResultado("Abaixo do peso ideal");
        }else if(resultadoDoCalculo > 18.5 && resultadoDoCalculo < 25){
            //Peso ideal
            mostraResultado("Peso ideal");
        }else if(resultadoDoCalculo >= 25 && resultadoDoCalculo < 30){
            //Sobrepeso
            mostraResultado("Sobrepeso");
        }else if(resultadoDoCalculo >= 30 && resultadoDoCalculo < 35){
            //Obesidade 1
            mostraResultado("Obesidade 1");
        }else if(resultadoDoCalculo >= 35 && resultadoDoCalculo < 40){
            //Obesidade 2
            mostraResultado("Obesidade 2");
        }else{
            //Obesidade 3
            mostraResultado("Obesidade 3");
        }
    }

    public void mostraResultado(String resultado){
        Toast.makeText(getApplicationContext(), "Você está: " + resultado, Toast.LENGTH_LONG).show();
    }
}
