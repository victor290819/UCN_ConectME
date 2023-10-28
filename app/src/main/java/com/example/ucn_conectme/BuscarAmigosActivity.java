package com.example.ucn_conectme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

public class BuscarAmigosActivity extends AppCompatActivity {
     private Toolbar toolbar;
    private RecyclerView Buscar_Amigos_ReciclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_amigos);

        Buscar_Amigos_ReciclerView = (RecyclerView)findViewById(R.id.buscar_amigo_reciclerview);
        Buscar_Amigos_ReciclerView
    }
}