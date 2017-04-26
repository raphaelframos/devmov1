package com.powellapps.listacustomizada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Cosmetico> cosmeticos;

    private RecyclerView recyclerView;
    private AdapterCosmetico adapterCosmetico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCosmeticos);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        cosmeticos = new ArrayList<>();
        Cosmetico cosmetico1 = new Cosmetico();
        cosmetico1.setMarca("Jequiti");
        cosmetico1.setNome("Perfume do Celso");
        cosmetico1.setValor("R$ 10,00");
        cosmetico1.setFoto("https://http2.mlstatic.com/cd-celso-portiolli-e-tempo-de-alegria-D_NQ_NP_789401-MLB20325044456_062015-F.jpg");
        cosmeticos.add(cosmetico1);

        Cosmetico cosmetico2 = new Cosmetico();
        cosmetico2.setMarca("Boticario");
        cosmetico2.setNome("Portinari");
        cosmetico2.setValor("R$ 15,00");
        cosmetico2.setFoto("http://boticario.vteximg.com.br/arquivos/ids/184999-1000-1000/Portinari-Des.-Colonia-28903.jpg");
        cosmeticos.add(cosmetico2);

        Cosmetico cosmetico3 = new Cosmetico();
        cosmetico3.setMarca("Avon");
        cosmetico3.setNome("Malbec");
        cosmetico3.setValor("R$ 20,00");
        cosmetico3.setFoto("http://boticario.vteximg.com.br/arquivos/ids/179579-1000-1000/20842.jpg");
        cosmeticos.add(cosmetico3);

        adapterCosmetico = new AdapterCosmetico(cosmeticos, getApplicationContext());
        recyclerView.setAdapter(adapterCosmetico);


    }
}
