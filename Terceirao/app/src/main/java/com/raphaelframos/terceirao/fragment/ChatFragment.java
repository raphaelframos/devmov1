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
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.raphaelframos.terceirao.MenuPrincipalActivity;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.adapter.ChatAdapter;
import com.raphaelframos.terceirao.model.Mensagem;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private ListView listViewChat;
    private FirebaseListAdapter<Mensagem> adapter;
    private DatabaseReference reference;


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


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("chat");
        Mensagem mensagem = new Mensagem();
        mensagem.setTexto("Teste novo chat");
        reference.push().setValue(mensagem);

        FirebaseListOptions<Mensagem> options = new FirebaseListOptions.Builder<Mensagem>()
                .setQuery(reference, Mensagem.class)
                .setLayout(R.layout.adapter_chat)
                .build();

        adapter = new FirebaseListAdapter<Mensagem>(options) {

            @Override
            protected void populateView(View v, Mensagem model, int position) {

                TextView textView = v.findViewById(R.id.text_view_mensagem);
                textView.setText(model.getTexto());

            }


        };

        listViewChat.setAdapter(adapter);
        adapter.startListening();

    }
}
