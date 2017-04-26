package com.powellapps.listacustomizada;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 25/04/17.
 */

public class AdapterCosmetico extends RecyclerView.Adapter<AdapterCosmetico.CosmeticoViewHolder>{

    private ArrayList<Cosmetico> cosmeticos;
    private Context context;

    public AdapterCosmetico(ArrayList<Cosmetico> cosmeticos, Context context) {
        this.cosmeticos = cosmeticos;
        this.context = context;
    }

    @Override
    public CosmeticoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cosmetico, parent, false);
        CosmeticoViewHolder viewHolder = new CosmeticoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CosmeticoViewHolder holder, final int position) {
        Cosmetico cosmetico = cosmeticos.get(position);
        holder.textViewMarca.setText(cosmetico.getMarca());
        holder.textViewValor.setText(cosmetico.getValor());
        holder.textViewNome.setText(cosmetico.getNome());
        Picasso.with(context).load(cosmetico.getFoto()).into(holder.imageViewFoto);
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cosmeticos.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cosmeticos.size();
    }

    public class CosmeticoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewFoto;
        private TextView textViewNome;
        private TextView textViewMarca;
        private TextView textViewValor;
        private ImageView imageViewDelete;

        public CosmeticoViewHolder(View itemView) {
            super(itemView);

            imageViewFoto = (ImageView) itemView.findViewById(R.id.imageViewFoto);
            textViewNome = (TextView) itemView.findViewById(R.id.textViewNome);
            textViewValor = (TextView) itemView.findViewById(R.id.textViewValor);
            textViewMarca = (TextView) itemView.findViewById(R.id.textViewMarca);
            imageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
