package com.raphaelframos.terceirao.fragment;


import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.raphaelframos.terceirao.MenuPrincipalActivity;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.adapter.ChatAdapter;
import com.raphaelframos.terceirao.banco_dados.BancoDeDados;
import com.raphaelframos.terceirao.model.Mensagem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private ListView listViewChat;
    private FirebaseListAdapter<Mensagem> adapter;
    private DatabaseReference reference;
    private Spinner spinnerEscola;
    private EditText editTextMensagem;
    private ImageView imageViewEnviar;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewChat = getView().findViewById(R.id.list_view_chat);
        spinnerEscola = getView().findViewById(R.id.spinner_escola);
        editTextMensagem = getView().findViewById(R.id.edit_mensagem);
        imageViewEnviar = getView().findViewById(R.id.image_enviar);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        spinnerEscola.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                reference = firebaseDatabase.getReference().child("chat").child(((String) spinnerEscola.getSelectedItem()));


                FirebaseListOptions<Mensagem> options = new FirebaseListOptions.Builder<Mensagem>()
                        .setQuery(reference, Mensagem.class)
                        .setLayout(R.layout.adapter_chat)
                        .build();

                adapter = new FirebaseListAdapter<Mensagem>(options) {

                    @Override
                    protected void populateView(View v, Mensagem model, int position) {

                        TextView textView = v.findViewById(R.id.text_view_mensagem);
                        TextView textViewNome = v.findViewById(R.id.text_view_nome);
                        ImageView imageViewFoto = v.findViewById(R.id.image_foto);
                        textView.setText(model.getTexto());
                        textViewNome.setText(model.getNome());
                        if(!model.getFoto().isEmpty()) {
                            Picasso.get().load(model.getFoto()).into(imageViewFoto);
                        }

                    }
                };
                listViewChat.setAdapter(adapter);
                adapter.startListening();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imageViewEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String texto = editTextMensagem.getText().toString();

                if(!texto.isEmpty() && !BancoDeDados.getInstance().getId(getActivity()).isEmpty()){
                    Mensagem mensagem = new Mensagem();
                    mensagem.setId(BancoDeDados.getInstance().getId(getActivity()));
                    mensagem.setFoto(BancoDeDados.getInstance().getFoto(getActivity()));
                    mensagem.setNome(BancoDeDados.getInstance().getNome(getActivity()));
                    mensagem.setTexto(texto);
                    reference.push().setValue(mensagem);
                    editTextMensagem.setText("");
                }


            }
        });

    }
}
