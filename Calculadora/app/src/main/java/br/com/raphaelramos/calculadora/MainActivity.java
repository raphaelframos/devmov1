package br.com.raphaelramos.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextVisor;
    Button buttonSoma;
    Button buttonIgual;
    TextView textViewHistorico;

    String primeiroNumero;
    String segundoNumero;
    String operador;
    String historico = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextVisor = (EditText) findViewById(R.id.editTextVisor);
        buttonIgual = (Button) findViewById(R.id.buttonIgual);
        buttonSoma = (Button) findViewById(R.id.buttonMais);
        textViewHistorico = (TextView) findViewById(R.id.textViewHistorico);

        buttonSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "+";
                primeiroNumero = editTextVisor.getText().toString();
                historico = primeiroNumero + " " + operador + " ";
                Toast.makeText(getApplicationContext(), "Primeiro numero " + primeiroNumero, Toast.LENGTH_LONG).show();
                editTextVisor.setText("");
                mostraValorNoHistorico();
            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundoNumero = editTextVisor.getText().toString();
                historico = historico + segundoNumero;
                Integer resultado = calcula();
                historico = historico + " = " + resultado;
                mostraValorNoHistorico();
                Toast.makeText(getApplicationContext(),"Resultado " + resultado, Toast.LENGTH_SHORT).show();
                editTextVisor.setText(resultado.toString());
            }
        });
    }

    public void mostraValorNoHistorico(){
        textViewHistorico.setText(historico);
    }

    public int calcula(){
        int primeiro = Integer.parseInt(primeiroNumero);
        int segundo = Integer.parseInt(segundoNumero);

        if(operador == "+"){
            return primeiro + segundo;
        }else if(operador == "-"){
            return primeiro - segundo;
        }
        return 0;
    }
}
