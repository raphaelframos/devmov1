package br.com.raphaelframos.carrinhodecompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
            hideKeyboard(this);
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
        textViewResultado.setText("");
        textViewProdutoDois.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cinza_escuro));
        textViewProdutoUm.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cinza_escuro));
    }

    public String defineMelhorPreco(String precoUm, String quantidadeUm, String precoDois, String quantidadeDois){
        double indiceProdutoUm = getDouble(precoUm) / getDouble(quantidadeUm);
        double indiceProdutoDois = getDouble(precoDois) / getDouble(quantidadeDois);

        String resultado = getString(R.string.melhor_preco_produto);
        resultado += " ";
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

    private double getDouble(String numero) {
        try{
            return Double.parseDouble(numero);
        }catch (Exception e){
            return 0.0;
        }
    }

    public boolean validaCampos(String precoUm, String precoDois, String quantidadeUm, String quantidadeDois){

        if(precoUm.isEmpty()){
            editTextPrecoUm.setError(getString(R.string.campo_branco));
            return false;
        }

        if(precoDois.isEmpty()){
            editTextPrecoDois.setError(getString(R.string.campo_branco));
            return false;
        }

        if(quantidadeUm.isEmpty()){
            editTextQuantidadeUm.setError(getString(R.string.campo_branco));
            return false;
        }

        if(quantidadeDois.isEmpty()){
            editTextQuantidadeDois.setError(getString(R.string.campo_branco));
            return false;
        }

        if(getDouble(precoUm) <= 0){
            editTextPrecoUm.setError(getString(R.string.numero_maior_zero));
            return false;
        }
        if(getDouble(precoDois) <= 0){
            editTextPrecoDois.setError(getString(R.string.numero_maior_zero));
            return false;
        }

        if(getDouble(quantidadeUm) <= 0){
            editTextQuantidadeUm.setError(getString(R.string.numero_maior_zero));
            return false;
        }

        if(getDouble(quantidadeDois) <= 0){
            editTextQuantidadeDois.setError(getString(R.string.numero_maior_zero));
            return false;
        }


        return true;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
