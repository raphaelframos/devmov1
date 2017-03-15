package br.com.raphaelramos.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        String meuNome = getIntent().getStringExtra("nome");
        textViewResultado.setText(meuNome);
    }

}
