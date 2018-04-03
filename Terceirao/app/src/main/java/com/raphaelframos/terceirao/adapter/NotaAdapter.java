package com.raphaelframos.terceirao.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raphaelframos.terceirao.NovaDisciplinaActivity;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 20/03/2018.
 */

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder>{

    private ArrayList<Disciplina> disciplinas;
    private Activity context;

    public NotaAdapter(Activity context, ArrayList<Disciplina> disciplinasDaActivity) {
        this.disciplinas = disciplinasDaActivity;
        this.context = context;
    }

    @Override
    public NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nota, parent, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotaViewHolder holder, int position) {

        final Disciplina disciplina = disciplinas.get(position);

        holder.textViewNome.setText(disciplina.getNome());
        holder.textViewMedia.setText(disciplina.getMedia()+ "");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Cliquei com click curto", Toast.LENGTH_LONG).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(context, NovaDisciplinaActivity.class);
                it.putExtra("disciplina", disciplina);
                context.startActivity(it);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return disciplinas.size();
    }

    public void atualiza(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
        notifyDataSetChanged();
    }

    public class NotaViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNome;
        private TextView textViewMedia;

        public NotaViewHolder(View itemView) {
            super(itemView);

            textViewMedia = itemView.findViewById(R.id.text_view_media);
            textViewNome = itemView.findViewById(R.id.text_view_nome_da_disciplina);
        }
    }
}
