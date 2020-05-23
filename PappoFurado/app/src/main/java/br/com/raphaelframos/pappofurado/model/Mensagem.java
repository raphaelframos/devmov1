package br.com.raphaelframos.pappofurado.model;

import androidx.annotation.NonNull;

public class Mensagem {

    private String id;
    private String texto;

    public Mensagem(){}

    public Mensagem(String texto) {
        setTexto(texto);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @NonNull
    @Override
    public String toString() {
        return getTexto();
    }
}
