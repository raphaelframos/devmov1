package br.com.raphaelframos.carrinhodecompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.raphaelframos.carrinhodecompras.bancodedados.ProdutoRepository;
import br.com.raphaelframos.carrinhodecompras.bancodedados.Singleton;
import br.com.raphaelframos.carrinhodecompras.model.Produto;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonNovo;
    private ListView listViewProdutos;
    private ArrayList<Produto> produtos;
    private ArrayAdapter<Produto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButtonNovo = findViewById(R.id.floatingActionButton_novo);
        listViewProdutos = findViewById(R.id.listView_produtos);

        criaAdapter();
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
                final Produto produtoEscolhido = produtos.get(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setMessage("Deseja excluir este produto?").setTitle("Atenção");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produtos.remove(produtoEscolhido);
                        adapter.notifyDataSetChanged();
                    }
                });
                alerta.setNegativeButton("Não", null);
                alerta.show();
                return true;
            }
        });

        floatingActionButtonNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NovoProdutoActivity.class));
            }
        });

        startActivity(new Intent(this, ListaProdutosActivity.class));
    }

    private void criaAdapter() {
        produtos = ProdutoRepository.getProdutos();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, produtos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAdapter();
    }

    private void atualizaAdapter() {
        produtos = ProdutoRepository.getProdutos();
        adapter.notifyDataSetChanged();
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
