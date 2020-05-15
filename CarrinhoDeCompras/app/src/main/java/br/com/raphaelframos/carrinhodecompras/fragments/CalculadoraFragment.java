package br.com.raphaelframos.carrinhodecompras.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import br.com.raphaelframos.carrinhodecompras.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculadoraFragment extends Fragment {

    public CalculadoraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculadora, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayout linearLayoutSaldo = getView().findViewById(R.id.linearLayout_saldo);
        linearLayoutSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogInformacoesFragment().show(getFragmentManager(), "info");
            }
        });
    }
}
