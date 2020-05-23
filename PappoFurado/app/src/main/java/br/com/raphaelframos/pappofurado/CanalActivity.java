package br.com.raphaelframos.pappofurado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.raphaelframos.pappofurado.model.Mensagem;

public class CanalActivity extends AppCompatActivity {

    private EditText editTextMensagem;
    private FloatingActionButton floatingActionButtonEnviar;
    private ListView listViewMensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canal);

        editTextMensagem = findViewById(R.id.editText_mensagem);
        floatingActionButtonEnviar = findViewById(R.id.fab_enviar);
        listViewMensagens = findViewById(R.id.listView_mensagens);

        ArrayList<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(new Mensagem("Oi"));
        mensagens.add(new Mensagem("Tudo bem?"));
        mensagens.add(new Mensagem("Tudo"));


        ArrayAdapter<Mensagem> adapter = new ArrayAdapter<Mensagem>(this, android.R.layout.simple_list_item_1, mensagens);
        listViewMensagens.setAdapter(adapter);
    }
}
