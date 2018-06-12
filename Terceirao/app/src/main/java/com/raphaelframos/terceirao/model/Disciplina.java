package com.raphaelframos.terceirao.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by raphaelramos on 20/03/2018.
 */

public class Disciplina implements Serializable{

    private String nome;
    private String nomeDoProfessor;
    private ArrayList<Double> notas = new ArrayList<>();

    public Disciplina(){}

    public Disciplina(String nome, double media) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Double calculaMedia() {

        if(notas == null || notas.size() == 0){
            return 0.0;
        }

        double media = 0;
        for(double nota : notas){
            media = media + nota;
        }
        media = media/notas.size();
        return media;
    }

    public Double getNota(int posicao){
        try{
            return notas.get(posicao-1);
        }catch (Exception e){
            return 0.0;
        }
    }

    public Double retornaNotaUm() {
        return getNota(1);
    }

    public Double retornaNotaDois() {
        return getNota(2);
    }

    public Double retornaNotaTres() {
        return getNota(3);
    }

    public Double retornaNotaQuatro() {
        return getNota(4);
    }

    public void adicionaNota(String nota) {
        if(!nota.isEmpty()) {
            try {
                adicionaNota(Double.parseDouble(nota));
            } catch (Exception e) {
                adicionaNota(0.0);
            }
        }
    }

    public String retornaMedia() {
        try{
            double media = 0;
            if(notas.size() > 0){
                for(Double nota : notas){
                    Log.v("terceirao", "Nota " + nota);
                    media += nota;
                }
                media = media/notas.size();
            }
            return media + "";
        }catch (Exception e){
            return "0";
        }
    }
}
