package br.com.raphaelframos.pappofurado;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
import java.util.List;

import br.com.raphaelframos.pappofurado.model.Mensagem;

public class CanalActivity extends AppCompatActivity {

    private EditText editTextMensagem;
    private FloatingActionButton floatingActionButtonEnviar;
    private ListView listViewMensagens;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canal);

        editTextMensagem = findViewById(R.id.editText_mensagem);
        floatingActionButtonEnviar = findViewById(R.id.fab_enviar);
        listViewMensagens = findViewById(R.id.listView_mensagens);
        listViewMensagens.setFocusable(false);
        listViewMensagens.setClickable(false);
        final String idDoCanal = getIntent().getStringExtra("id");

        db = FirebaseFirestore.getInstance();

        db.collection("canais").document(idDoCanal).collection("mensagens").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                List<Mensagem> mensagens = queryDocumentSnapshots.toObjects(Mensagem.class);

                final ArrayAdapter<Mensagem> adapter = new ArrayAdapter<Mensagem>(getApplicationContext(), android.R.layout.simple_list_item_1, mensagens);
                listViewMensagens.setAdapter(adapter);
            }
        });



        floatingActionButtonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editTextMensagem.getText().toString();
                Mensagem mensagem = new Mensagem();
                mensagem.setTexto(texto);
                db.collection("canais").document(idDoCanal).collection("mensagens").add(mensagem);
                editTextMensagem.setText("");
            }
        });
    }
}
