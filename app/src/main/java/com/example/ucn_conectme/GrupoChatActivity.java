package com.example.ucn_conectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class GrupoChatActivity extends AppCompatActivity {

    private ImageButton enviarmensaje;
    private EditText mensajeUsuario;
    private ScrollView scrollView;

    private TextView vermensajes,titulogrupo;

    private String CurrentGrupoNombre, CurrenUserID, CurrenUserName,Fecha,Hora;
    private FirebaseAuth auth;
    private DatabaseReference Userref,Gruporef, GrupoMensajekey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_chat);

        CurrentGrupoNombre = getIntent().getExtras().get("nombregrupo").toString();
        auth = FirebaseAuth.getInstance();
        CurrenUserID = auth.getCurrentUser().getUid();
        Userref = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        Gruporef=FirebaseDatabase.getInstance().getReference().child("Grupos").child(CurrentGrupoNombre);

        informaciouser();
        iniciarobjetos();
        enviarmensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarMensajeDB();
                mensajeUsuario.setText("");

            }
        });
    }

    private void GuardarMensajeDB() {
            String mensaje = mensajeUsuario.getText().toString();
            String mensajekey=Gruporef.push().getKey();
        if (TextUtils.isEmpty(mensaje)){
            Toast.makeText(this,"ingrese un mensaje",Toast.LENGTH_SHORT).show();

        }else{
            Calendar fechacalendar = Calendar.getInstance();
            SimpleDateFormat Currentfecha = new SimpleDateFormat("MMM dd,yyyy");
            Fecha=Currentfecha.format(fechacalendar.getTime());

            Calendar horacalendar = Calendar.getInstance();
            SimpleDateFormat Currenthora = new SimpleDateFormat("hh:mm a");
            Hora=Currenthora.format(horacalendar.getTime());

            HashMap<String,Object> mensajegrupo= new HashMap<>();
            Gruporef.updateChildren (mensajegrupo) ;
            GrupoMensajekey=Gruporef.child(mensajekey) ;

            HashMap<String,Object>mensajeinformacion= new HashMap<>();
            mensajeinformacion.put("nombre",CurrenUserName);
            mensajeinformacion.put("mensaje",mensaje);
            mensajeinformacion.put("Fecha", Fecha);
            mensajeinformacion.put("hora", Hora);
            GrupoMensajekey.updateChildren(mensajeinformacion);



        }
    }

    private void iniciarobjetos() {
        titulogrupo=(TextView)findViewById(R.id.grupo_chat_bar_layout);
        enviarmensaje=(ImageButton) findViewById(R.id.enviar_mensaje_grupo);
        mensajeUsuario=(EditText)findViewById(R.id.texto_grupo_chat);
        scrollView=(ScrollView)findViewById(R.id.mi_scroll_view);
        vermensajes=(TextView) findViewById(R.id.grupo_chat_texto);
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