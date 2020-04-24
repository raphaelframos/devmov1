package br.com.raphaelframos.carrinhodecompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

import br.com.raphaelframos.carrinhodecompras.model.Produto;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonNovo;
    private ListView listViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButtonNovo = findViewById(R.id.floatingActionButton_novo);
        listViewProdutos = findViewById(R.id.listView_produtos);

        final ArrayList<Produto> produtos = new ArrayList<>();
        Produto produto = new Produto();
        produto.setNome("Coca cola");
        produto.setQuantidade(2);
        produto.setUnidade("Un");
        produtos.add(produto);

        Produto produto2 = new Produto();
        produto2.setNome("Pepsi");
        produto2.setQuantidade(5);
        produto2.setUnidade("Un");
        produtos.add(produto2);

        Produto produto3 = new Produto();
        produto3.setNome("Detergente");
        produto3.setQuantidade(2);
        produto3.setUnidade("L");
        produtos.add(produto3);

        Produto produto4 = new Produto();
        produto4.setNome("Batata");
        produto4.setQuantidade(2.5);
        produto4.setUnidade("Kg");
        produtos.add(produto4);

        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, produtos);
        listViewProdutos.setAdapter(adapter);
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Produto produtoEscolhido = produtos.get(posicao);
                Toast.makeText(getApplicationContext(), "Oi. Sou o produto com nome de " + produtoEscolhido.getNome(), Toast.LENGTH_LONG).show();
            }
        });

        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Se voce acha que cachaça é água... cachaça n é agua não", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        floatingActionButtonNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NovoProdutoActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_melhor_preco:
                Intent it = new Intent(this, CalculoActivity.class);
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
