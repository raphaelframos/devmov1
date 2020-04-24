package br.com.raphaelframos.carrinhodecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoProdutoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextQuantidade;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);
        vinculaViewPeloId();

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String quantidadeDigitada = editTextQuantidade.getText().toString();
                if(valido(nome, quantidadeDigitada)){

                }else{
                    Toast.makeText(NovoProdutoActivity.this, "Campo inválido!", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean valido(String nome, String quantidadeDigitada) {
                if(nome.isEmpty()){
                    editTextNome.setError(getString(R.string.campo_branco));
                    return false;
                }

                if(quantidadeDigitada.isEmpty()){
                    editTextQuantidade.setError(getString(R.string.campo_branco));
                    return false;
                }

                double quantidade = Double.valueOf(quantidadeDigitada);
                if(quantidade <= 0){
                    editTextQuantidade.setError("Quantidade inválida!");
                    return false;
                }

                return true;
            }
        });
    }

    private void vinculaViewPeloId(){
        editTextNome = findViewById(R.id.editTextNome);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        buttonSalvar = findViewById(R.id.buttonSalvar);
    }
}
