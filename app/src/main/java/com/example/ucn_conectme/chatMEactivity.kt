package com.example.ucn_conectme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class chatMEactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_meactivity)

        val ftperfil=findViewById<ImageView>(R.id.ftperfil)
        val NUser = findViewById<TextView>(R.id.NUser)
        val mensajes= findViewById<RecyclerView>(R.id.rbmensajes)
        val txtmensaje = findViewById<EditText>(R.id.txtmensaje)
        val BtnEnviar = findViewById<ImageButton>(R.id.btnmensaje)
    }
}