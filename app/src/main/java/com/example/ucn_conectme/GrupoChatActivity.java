package com.example.ucn_conectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GrupoChatActivity extends AppCompatActivity {

    private String CurrentGrupoNombre, CurrenUserID, CurrenUserName;
    private FirebaseAuth auth;
    private DatabaseReference Userref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_chat);

        CurrentGrupoNombre = getIntent().getExtras().get("nombregrupo").toString();
        auth = FirebaseAuth.getInstance();
        CurrenUserID = auth.getCurrentUser().getUid();
        Userref = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        informaciouser();



    }

    private void informaciouser() {
        Userref.child(CurrenUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    CurrenUserName=snapshot.child("nombre").getValue().toString();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}