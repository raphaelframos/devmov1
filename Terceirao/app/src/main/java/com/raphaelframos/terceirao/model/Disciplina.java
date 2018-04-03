package com.raphaelframos.terceirao.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by raphaelramos on 20/03/2018.
 */

public class Disciplina implements Serializable{

    private String nome;
    private String nomeDoProfessor;
    private double media;
    private ArrayList<Double> notas = new ArrayList<>();

    public Disciplina(){}

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

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public String getNomeDoProfessor() {
        return nomeDoProfessor;
    }

    public void setNomeDoProfessor(String nomeDoProfessor) {
        this.nomeDoProfessor = nomeDoProfessor;
    }

    public void adicionaNota(double nota) {
        if(notas == null){
            notas = new ArrayList<>();
        }

        notas.add(nota);
    }

    public void calculaMedia() {

        double media = 0;
        for(double nota : notas){
            media = media + nota;
        }
        media = media/notas.size();
    }
}
