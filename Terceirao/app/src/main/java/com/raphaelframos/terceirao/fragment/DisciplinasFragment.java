package com.raphaelframos.terceirao.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raphaelframos.terceirao.NovaDisciplinaActivity;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.adapter.NotaAdapter;
import com.raphaelframos.terceirao.banco_dados.BancoDeDados;
import com.raphaelframos.terceirao.model.Disciplina;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisciplinasFragment extends Fragment {

    private static String TAG = "terceirao";

    private NotaAdapter notaAdapter;
    private ArrayList<Disciplina> disciplinas;

    public DisciplinasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disciplinas, container, false);

        RecyclerView recyclerViewDisciplinas = view.findViewById(R.id.recycler_disciplinas);
        recyclerViewDisciplinas.setLayoutManager(new LinearLayoutManager(getContext()));

        notaAdapter = new NotaAdapter(getActivity(), new ArrayList<Disciplina>());
        recyclerViewDisciplinas.setAdapter(notaAdapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_config), Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(getString(R.string.id), "");
        DatabaseReference myRef = database.getReference("disciplinas").child(id);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                disciplinas = new ArrayList<>();
                for(DataSnapshot disciplina: dataSnapshot.getChildren()){
                    disciplinas.add(disciplina.getValue(Disciplina.class));

                }
                notaAdapter.atualiza(disciplinas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_adicionar:
                startActivity(new Intent(getContext(), NovaDisciplinaActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_materia, menu);

    }
}
