package com.raphaelframos.terceirao.banco_dados;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.raphaelframos.terceirao.R;

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



    public boolean temId(Activity activity) {
        return !getId(activity).isEmpty();
    }

    public String getId(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.shared_config), Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(activity.getString(R.string.id), "");
        return result;
    }

    public static SharedPreferences.Editor getEditor(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.shared_config), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor;
    }

    public void salvaNome(Activity activity, String nome) {
        getEditor(activity).putString(activity.getString(R.string.nome), nome).apply();
    }

    public void salvaFoto(Activity activity, String foto) {
        getEditor(activity).putString(activity.getString(R.string.foto), foto).apply();
    }

    public String getNome(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.shared_config), Context.MODE_PRIVATE);
        return sharedPreferences.getString(activity.getString(R.string.nome), "");
    }

    public String getFoto(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.shared_config), Context.MODE_PRIVATE);
        return sharedPreferences.getString(activity.getString(R.string.foto), "");
    }
}
