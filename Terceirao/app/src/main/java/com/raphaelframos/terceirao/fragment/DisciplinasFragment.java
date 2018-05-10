package com.raphaelframos.terceirao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raphaelframos.terceirao.NovaDisciplinaActivity;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.adapter.NotaAdapter;
import com.raphaelframos.terceirao.banco_dados.BancoDeDados;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisciplinasFragment extends Fragment {


    public DisciplinasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disciplinas, container, false);

        RecyclerView recyclerViewDisciplinas = view.findViewById(R.id.recycler_disciplinas);
        recyclerViewDisciplinas.setLayoutManager(new LinearLayoutManager(getContext()));

        NotaAdapter notaAdapter = new NotaAdapter(getActivity(), BancoDeDados.getInstance().criaDisciplinasDemo());
        recyclerViewDisciplinas.setAdapter(notaAdapter);
        setHasOptionsMenu(true);
        return view;
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
