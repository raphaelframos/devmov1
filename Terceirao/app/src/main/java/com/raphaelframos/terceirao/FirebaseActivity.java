package com.raphaelframos.terceirao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.model.Disciplina;
import com.raphaelframos.terceirao.model.Escola;

import java.util.ArrayList;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_config), Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(getString(R.string.id), "");




        DatabaseReference myRef = database.getReference("disciplinas");

        Disciplina mat = new Disciplina();
        mat.setNomeDoProfessor("Alexandre");
        mat.setNome("Matemática");

        database.getReference().child(id).child("disciplinas").push().setValue(mat);

        Disciplina port = new Disciplina();
        port.setNome("Português");
        port.setNomeDoProfessor("Renato");

        Escola escola = new Escola();
        escola.setNome("10 de maio");
        escola.setEndereco("Av. Cardoso Moreira");

        Escola escola1 = new Escola();
        escola1.setEndereco("Governador");
        escola1.setNome("Padre Mello");

        DatabaseReference referenceEscola = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Disciplina> disciplinas = new ArrayList<>();
                for(DataSnapshot dado : dataSnapshot.getChildren()){
                    Disciplina disciplina = dado.getValue(Disciplina.class);
                    disciplinas.add(disciplina);
                    Log.v("terceirao", "\n\n\n\nDisciplina " +  disciplina.getNomeDoProfessor());


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("terceirao", "Deu merda!");

            }
        });



    }
}
