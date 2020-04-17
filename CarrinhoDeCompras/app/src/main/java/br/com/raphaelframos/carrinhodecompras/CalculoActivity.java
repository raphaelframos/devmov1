package br.com.raphaelframos.carrinhodecompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private TextView textViewProdutoUm;
    private TextView textViewProdutoDois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        editTextPrecoUm = findViewById(R.id.editTextPreco1);
        editTextPrecoDois = findViewById(R.id.editTextPreco2);
        editTextQuantidadeUm = findViewById(R.id.editTextQuantidade1);
        editTextQuantidadeDois = findViewById(R.id.editTextQuantidade2);
        textViewResultado = findViewById(R.id.textViewResultado);
        textViewProdutoUm = findViewById(R.id.textView_produto_um);
        textViewProdutoDois = findViewById(R.id.textView_produto_dois);
    }

    public void calcular(View view){

        String precoUm = editTextPrecoUm.getText().toString();
        String precoDois = editTextPrecoDois.getText().toString();
        String quantidadeUm = editTextQuantidadeUm.getText().toString();
        String quantidadeDois = editTextQuantidadeDois.getText().toString();

        if(validaCampos(precoUm, precoDois, quantidadeUm, quantidadeDois)){
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
        editTextPrecoUm.requestFocus();
    }

    public String defineMelhorPreco(String precoUm, String quantidadeUm, String precoDois, String quantidadeDois){
        double indiceProdutoUm = Double.parseDouble(precoUm)/Double.parseDouble(quantidadeUm);
        double indiceProdutoDois = Double.parseDouble(precoDois)/Double.parseDouble(quantidadeDois);

        String resultado = "O melhor produto é o ";

        if(indiceProdutoUm < indiceProdutoDois){
            resultado += "1";
            textViewProdutoUm.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.verde));
            textViewProdutoDois.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.vermelho));
        }else{
            textViewProdutoUm.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.vermelho));
            textViewProdutoDois.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.verde));
            resultado += "2";
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_limpar:
                limpaCampos();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
