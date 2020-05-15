package br.com.raphaelframos.carrinhodecompras.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.raphaelframos.carrinhodecompras.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogInformacoesFragment extends DialogFragment {

    public DialogInformacoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_informacoes, container, false);
    }
}
