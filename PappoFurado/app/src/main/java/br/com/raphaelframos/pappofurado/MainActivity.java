package br.com.raphaelframos.pappofurado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.raphaelframos.pappofurado.model.Canal;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNomeDoCanal;
    private Spinner spinnerCategoria;
    private ImageView imageViewCriar;
    private ListView listViewCanais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincularViewPeloId();
        ArrayList<Canal> canais = new ArrayList<>();
        canais.add(new Canal("STF", "Política"));
        canais.add(new Canal("Flamengo", "Esporte"));
        ArrayAdapter<Canal> adapter = new ArrayAdapter<Canal>(this, android.R.layout.simple_list_item_1, canais);
        listViewCanais.setAdapter(adapter);

    }

    private void vincularViewPeloId(){
        editTextNomeDoCanal = findViewById(R.id.editText_nome_canal);
        spinnerCategoria = findViewById(R.id.spinner_categorias);
        imageViewCriar = findViewById(R.id.imageView_criar);
        listViewCanais = findViewById(R.id.listView_canais);
    }
}
