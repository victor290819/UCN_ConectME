package com.example.ucn_conectme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;



public class DatosuserActivity extends AppCompatActivity {
    private EditText nombreper, telefonoper, carreraper;
    private Button Btnguardarinfo;


    private FirebaseAuth auth;
    private DatabaseReference userref;
    private ProgressDialog dialog;
    private String CurrentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datosuser);

        nombreper = (EditText) findViewById(R.id.nombrePer);
        telefonoper = (EditText) findViewById(R.id.telefonoper);
        carreraper = (EditText) findViewById(R.id.carreraper);
        Btnguardarinfo = (Button) findViewById(R.id.BtnGuardarinfo);


        dialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        CurrentUserID = auth.getCurrentUser().getUid();
        userref = FirebaseDatabase.getInstance().getReference().child("Usuarios");


        Btnguardarinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GuardarinformacionDB();

            }

        });


    }


    private void GuardarinformacionDB() {

        String nom = nombreper.getText().toString();
        String tel = telefonoper.getText().toString();
        String carr = carreraper.getText().toString();

        if (TextUtils.isEmpty(nom))
            Toast.makeText(this, "debe de ingresar un Nombre", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(tel)) {
            Toast.makeText(this, "debe de ingresar un Telefono", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(carr)) {
            Toast.makeText(this, "debe de ingresar la Carrera", Toast.LENGTH_SHORT).show();
        } else {
            HashMap map = new HashMap();
            map.put("nombre", nom);
            map.put("telefono", tel);
            map.put("carrera", carr);

            userref.updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(DatosuserActivity.this, "completado", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        lanzar();
                    } else {
                        Toast.makeText(DatosuserActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void lanzar(){
        Intent intentchat = new Intent(DatosuserActivity.this, MainActivity.class);
        startActivity(intentchat);
        finish();

    }
}









