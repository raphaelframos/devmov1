package com.raphaelframos.terceirao.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.raphaelframos.terceirao.MenuPrincipalActivity;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.fragment.DisciplinasFragment;

public class FragmentoUtils {

    public static void replace(FragmentActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmento, fragment).commit();
    }
}
