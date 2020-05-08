package br.com.raphaelframos.carrinhodecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.raphaelframos.carrinhodecompras.bancodedados.ProdutoRepository;
import br.com.raphaelframos.carrinhodecompras.bancodedados.Singleton;
import br.com.raphaelframos.carrinhodecompras.model.Produto;

public class NovoProdutoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextQuantidade;
    private Spinner spinnerUnidades;
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
                    Produto produto = new Produto().addNome(nome).addQuantidade(quantidadeDigitada).addUnidade(spinnerUnidades.getSelectedItem().toString());
                    ProdutoRepository.add(produto);
                    limpaCampos();
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

    private void limpaCampos() {
        editTextQuantidade.setText("1");
        editTextNome.setText("");
        spinnerUnidades.setSelection(0);
    }

    private void vinculaViewPeloId(){
        editTextNome = findViewById(R.id.editTextNome);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        spinnerUnidades = findViewById(R.id.spinnerUnidades);
        buttonSalvar = findViewById(R.id.buttonSalvar);
    }
}
