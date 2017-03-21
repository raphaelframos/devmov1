package com.powellapps.alunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] alunos = {"Kamile", "Dalmiro", "Henrique", "Carlos", "Adriele"};
    private ListView listViewAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewAlunos = (ListView) findViewById(R.id.listAlunos);
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, alunos);
        listViewAlunos.setAdapter(adapter);
    }
}
