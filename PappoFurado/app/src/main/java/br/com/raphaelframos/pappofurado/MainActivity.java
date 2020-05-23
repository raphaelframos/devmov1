package br.com.raphaelframos.pappofurado;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.raphaelframos.pappofurado.model.Canal;

public class MainActivity extends AppCompatActivity {

    private static final String CANAIS = "canais";
    private EditText editTextNomeDoCanal;
    private Spinner spinnerCategoria;
    private FloatingActionButton floatingActionButtonCriar;
    private ListView listViewCanais;
    // Access a Cloud Firestore instance from your Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        vincularViewPeloId();

        db.collection(CANAIS).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Canal> canais = queryDocumentSnapshots.toObjects(Canal.class);
                ArrayAdapter<Canal> adapter = new ArrayAdapter<Canal>(getApplicationContext(), android.R.layout.simple_list_item_1, canais);
                listViewCanais.setAdapter(adapter);
            }
        });


        listViewCanais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), CanalActivity.class));
            }
        });

        floatingActionButtonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = (String) spinnerCategoria.getSelectedItem();
                String nome = editTextNomeDoCanal.getText().toString();
                Canal canal = new Canal(nome, categoria);

                db.collection(CANAIS).add(canal);
            }
        });

    }

    private void vincularViewPeloId(){
        editTextNomeDoCanal = findViewById(R.id.editText_nome_canal);
        spinnerCategoria = findViewById(R.id.spinner_categorias);
        floatingActionButtonCriar = findViewById(R.id.fab_criar);
        listViewCanais = findViewById(R.id.listView_canais);
    }
}
