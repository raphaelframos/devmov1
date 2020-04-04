package br.com.raphaelframos.carrinhodecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalculoActivity extends AppCompatActivity {

    private EditText editTextPrecoUm;
    private EditText editTextPrecoDois;
    private EditText editTextQuantidadeUm;
    private EditText editTextQuantidadeDois;
    private TextView textViewResultado;
    private LinearLayout linearLayoutProdutoUm;
    private LinearLayout linearLayoutProdutoDois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        editTextPrecoUm = findViewById(R.id.editTextPreco1);
        editTextPrecoDois = findViewById(R.id.editTextPreco2);
        editTextQuantidadeUm = findViewById(R.id.editTextQuantidade1);
        editTextQuantidadeDois = findViewById(R.id.editTextQuantidade2);
        textViewResultado = findViewById(R.id.textViewResultado);
        linearLayoutProdutoUm = findViewById(R.id.linearLayoutProdutoUm);
        linearLayoutProdutoDois = findViewById(R.id.linearLayoutProdutoDois);

    }

    public void calcular(View view){

        String precoUm = editTextPrecoUm.getText().toString();
        String precoDois = editTextPrecoDois.getText().toString();
        String quantidadeUm = editTextQuantidadeUm.getText().toString();
        String quantidadeDois = editTextQuantidadeDois.getText().toString();

        if(validaCampos(precoUm, precoDois, quantidadeUm, quantidadeDois)){
            limpaCampos();
            String resultado = defineMelhorPreco(precoUm, quantidadeUm, precoDois, quantidadeDois);
            textViewResultado.setText(resultado);
        }else{
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
        }


    }

    private void limpaCampos() {
        editTextQuantidadeDois.setText("");
        editTextQuantidadeUm.setText("");
        editTextPrecoDois.setText("");
        editTextPrecoUm.setText("");
        linearLayoutProdutoUm.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
        linearLayoutProdutoDois.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
    }

    public String defineMelhorPreco(String precoUm, String quantidadeUm, String precoDois, String quantidadeDois){
        double indiceProdutoUm = Double.parseDouble(precoUm)/Double.parseDouble(quantidadeUm);
        double indiceProdutoDois = Double.parseDouble(precoDois)/Double.parseDouble(quantidadeDois);

        String resultado = "O melhor produto é o ";

        if(indiceProdutoUm < indiceProdutoDois){
            resultado += "1";
            linearLayoutProdutoUm.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        }else{
            resultado += "2";
            linearLayoutProdutoDois.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        }
        return resultado;

    }

    public boolean validaCampos(String precoUm, String precoDois, String quantidadeUm, String quantidadeDois){

        if(precoUm.isEmpty()){
            editTextPrecoUm.setError("Campo em branco!");
            return false;
        }

        if(precoDois.isEmpty()){
            editTextPrecoDois.setError("Campo em branco!");
            return false;
        }

        if(quantidadeUm.isEmpty()){
            editTextQuantidadeUm.setError("Campo em branco!");
            return false;
        }

        if(quantidadeDois.isEmpty()){
            editTextQuantidadeDois.setError("Campo em branco!");
            return false;
        }
        return true;
    }



}
