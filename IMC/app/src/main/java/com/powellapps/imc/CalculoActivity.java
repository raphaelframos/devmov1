package com.powellapps.imc;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculoActivity extends AppCompatActivity {

    private EditText editTextAltura;
    private EditText editTextPeso;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
        encontraViewsDoXMLPeloId();
      //  buttonCalcular.setOnClickListener(calculoIMC);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultado = calculaIMC();
                Intent intent = new Intent(CalculoActivity.this, ResultadoActivity.class);
                intent.putExtra("resultadoDoCalculo", resultado);
                startActivity(intent);
            }
        });
    }
    /*
    private View.OnClickListener calculoIMC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double resultado = calculaIMC();
            Intent intent = new Intent(CalculoActivity.this, ResultadoActivity.class);
            intent.putExtra("resultadoDoCalculo", resultado);
            startActivity(intent);
        }
    };
    */

    public double calculaIMC(){
        double resultado = 0;
        String alturaDigitada = editTextAltura.getText().toString();
        String pesoDigitada = editTextPeso.getText().toString();
        double altura = Double.valueOf(alturaDigitada);
        double peso = Double.valueOf(pesoDigitada);
        resultado = (peso/(altura*altura));
        return resultado;
    }

    public void encontraViewsDoXMLPeloId(){
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
    }
}
