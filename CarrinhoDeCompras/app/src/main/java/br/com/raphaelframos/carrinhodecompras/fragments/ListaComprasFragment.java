package br.com.raphaelframos.carrinhodecompras.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.raphaelframos.carrinhodecompras.R;
import br.com.raphaelframos.carrinhodecompras.model.ItemDoPedido;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaComprasFragment extends Fragment {

    public ListaComprasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_compras, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listViewCompras = getView().findViewById(R.id.listView_compras);
        ArrayList<ItemDoPedido> itens = new ArrayList<>();
        itens.add(new ItemDoPedido());
        itens.add(new ItemDoPedido());
        ArrayAdapter<ItemDoPedido> itemAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itens);
        listViewCompras.setAdapter(itemAdapter);
    }
}
