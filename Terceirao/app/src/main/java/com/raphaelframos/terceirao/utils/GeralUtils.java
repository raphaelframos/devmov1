package com.raphaelframos.terceirao.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.raphaelframos.terceirao.R;

public class GeralUtils {

    private static final double MEDIA = 50;

    public static int getCorPeloValor(Context context, double valor){
        if(valor < MEDIA){
            return ContextCompat.getColor(context, R.color.vermelho);
        }
        return ContextCompat.getColor(context, R.color.azul);
    }
}
