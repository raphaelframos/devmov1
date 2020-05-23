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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import br.com.raphaelframos.pappofurado.model.Canal;

public class MainActivity extends AppCompatActivity {

    private static final String CANAIS = "canais";
    private EditText editTextNomeDoCanal;
    private Spinner spinnerCategoria;
    private FloatingActionButton floatingActionButtonCriar;
    private ListView listViewCanais;
    private List<Canal> canais = new ArrayList<>();
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
                canais = queryDocumentSnapshots.toObjects(Canal.class);
                ArrayAdapter<Canal> adapter = new ArrayAdapter<Canal>(getApplicationContext(), android.R.layout.simple_list_item_1, canais);
                listViewCanais.setAdapter(adapter);
            }
        });


        listViewCanais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Canal canal = canais.get(position);
                Intent it = new Intent(getApplicationContext(), CanalActivity.class);
                it.putExtra("id", canal.getId());
                startActivity(it);
            }
        });

        floatingActionButtonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = (String) spinnerCategoria.getSelectedItem();
                String nome = editTextNomeDoCanal.getText().toString();
                final Canal canal = new Canal(nome, categoria);
                db.collection(CANAIS).add(canal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String id = documentReference.getId();
                        canal.setId(id);
                        db.collection(CANAIS).document(id).set(canal);
                    }
                });
                iniciaCampos();
            }
        });

    }

    private void iniciaCampos() {
        editTextNomeDoCanal.setText("");
        spinnerCategoria.setSelection(0);
    }

    private void vincularViewPeloId(){
        editTextNomeDoCanal = findViewById(R.id.editText_nome_canal);
        spinnerCategoria = findViewById(R.id.spinner_categorias);
        floatingActionButtonCriar = findViewById(R.id.fab_criar);
        listViewCanais = findViewById(R.id.listView_canais);
    }
}
