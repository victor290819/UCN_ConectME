package com.example.ucn_conectme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class GrupoChatActivity extends AppCompatActivity {

    private  String CurrentGrupoNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_chat);

        CurrentGrupoNombre= getIntent().getExtras().get("nombregrupo").toString();
        Toast.makeText(this, CurrentGrupoNombre, Toast.LENGTH_SHORT).show();
    }
}