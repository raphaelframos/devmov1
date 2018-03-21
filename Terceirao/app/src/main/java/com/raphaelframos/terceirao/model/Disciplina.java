package com.raphaelframos.terceirao.model;

/**
 * Created by raphaelramos on 20/03/2018.
 */

public class Disciplina {

    private String nome;
    private double media;

    public Disciplina(String nome, double media) {
        setNome(nome);
        setMedia(media);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
