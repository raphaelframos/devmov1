package com.raphaelframos.terceirao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.model.Mensagem;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private ArrayList<Mensagem> mensagens;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Mensagem mensagem = mensagens.get(position);

        holder.textViewMensagem.setText(mensagem.getTexto());
    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewMensagem;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewMensagem = itemView.findViewById(R.id.text_view_mensagem);
        }
    }
}
