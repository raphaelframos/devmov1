package com.powellapps.alunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] alunos = {"Kamile", "Dalmiro", "Henrique", "Carlos", "Adriele", "Pedro"};
    private ListView listViewAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewAlunos = (ListView) findViewById(R.id.listAlunos);

        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, alunos);

        listViewAlunos.setAdapter(adapter);
        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aluno = alunos[position];
                Toast.makeText(getApplicationContext(), "Teste " + aluno, Toast.LENGTH_LONG).show();
            }
        });

        listViewAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Teste 2", Toast.LENGTH_LONG).show();

                return false;
            }
        });
       // listViewAlunos.
    }
}
