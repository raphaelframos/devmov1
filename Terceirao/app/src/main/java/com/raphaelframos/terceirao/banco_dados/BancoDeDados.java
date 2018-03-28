package com.raphaelframos.terceirao.banco_dados;

import com.raphaelframos.terceirao.model.Disciplina;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 26/03/2018.
 */

public class BancoDeDados {

    private ArrayList<String> nomeDasDisciplinas;
    public static BancoDeDados instance;

    public static BancoDeDados getInstance(){
        if(instance == null){
            instance = new BancoDeDados();
        }

        return instance;
    }

    public void salva(String nomeDaDisciplina) {
        if(nomeDasDisciplinas == null) {
            nomeDasDisciplinas = new ArrayList<>();
        }

        nomeDasDisciplinas.add(nomeDaDisciplina);
    }

    public ArrayList<String> getNomeDasDisciplinas(){
        return nomeDasDisciplinas;
    }

    public ArrayList<Disciplina> getDisciplinas(){
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        if(nomeDasDisciplinas != null && nomeDasDisciplinas.size() > 0){
            for(String nome : nomeDasDisciplinas){
                Disciplina disciplina = new Disciplina(nome, 10);
                disciplinas.add(disciplina);
            }
        }

        return disciplinas;
    }

    public ArrayList<Disciplina> criaDisciplinasDemo() {
        Disciplina disciplina = new Disciplina("Portugues", 10);
        Disciplina disciplina1 = new Disciplina("Matemática", 8);
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(disciplina);
        disciplinas.add(disciplina1);
        disciplinas.add(disciplina);
        disciplinas.add(disciplina1);
        disciplinas.add(disciplina);
        disciplinas.add(disciplina1);
        return disciplinas;
    }
}
