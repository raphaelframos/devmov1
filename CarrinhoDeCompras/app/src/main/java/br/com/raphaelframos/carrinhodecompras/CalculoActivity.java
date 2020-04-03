package br.com.raphaelframos.carrinhodecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoActivity extends AppCompatActivity {

    private EditText editTextPrecoUm;
    private EditText editTextPrecoDois;
    private EditText editTextQuantidadeUm;
    private EditText editTextQuantidadeDois;
    private Button buttonCalcular;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        editTextPrecoUm = findViewById(R.id.editTextPreco1);
        editTextPrecoDois = findViewById(R.id.editTextPreco2);
        editTextQuantidadeUm = findViewById(R.id.editTextQuantidade1);
        editTextQuantidadeDois = findViewById(R.id.editTextQuantidade2);
        textViewResultado = findViewById(R.id.textViewResultado);
        buttonCalcular = findViewById(R.id.buttonCalcular);

    }

    public void calcular(View view){
        String precoUm = editTextPrecoUm.getText().toString();
        String precoDois = editTextPrecoDois.getText().toString();
        String quantidadeUm = editTextQuantidadeUm.getText().toString();
        String quantidadeDois = editTextQuantidadeDois.getText().toString();


        
        textViewResultado.setText("Sai da minha aba! " + precoUm);
    }




}
