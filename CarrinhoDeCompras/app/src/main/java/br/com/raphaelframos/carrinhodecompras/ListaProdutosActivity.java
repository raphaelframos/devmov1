package br.com.raphaelframos.carrinhodecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.raphaelframos.carrinhodecompras.fragments.CalculadoraFragment;
import br.com.raphaelframos.carrinhodecompras.fragments.ListaComprasFragment;

public class ListaProdutosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout_acima, new ListaComprasFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout_abaixo, new CalculadoraFragment()).commit();
    }
}
