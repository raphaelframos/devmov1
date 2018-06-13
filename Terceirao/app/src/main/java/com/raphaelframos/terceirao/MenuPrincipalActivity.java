package com.raphaelframos.terceirao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.raphaelframos.terceirao.fragment.ChatFragment;
import com.raphaelframos.terceirao.fragment.DisciplinasFragment;
import com.raphaelframos.terceirao.fragment.PerfilFragment;
import com.raphaelframos.terceirao.utils.FragmentoUtils;

public class MenuPrincipalActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentoUtils.replace(MenuPrincipalActivity.this, new DisciplinasFragment());
                    return true;
                case R.id.navigation_dashboard:
                    FragmentoUtils.replace(MenuPrincipalActivity.this, new ChatFragment());
                    return true;
                case R.id.navigation_notifications:
                    FragmentoUtils.replace(MenuPrincipalActivity.this, new PerfilFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentoUtils.replace(MenuPrincipalActivity.this, new DisciplinasFragment());

     //   startActivity(new Intent(this, FirebaseActivity.class));


    }

}
