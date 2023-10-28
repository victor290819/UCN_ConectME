package com.example.ucn_conectme

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ucn_conectme.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)

        binding.btnlogin.setOnClickListener {
            val email = binding.txtusuario.text.toString()
            val password = binding.txtContraseA.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            }
        }


    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    val intent = Intent(this, DatosuserActivity::class.java)
                    startActivity(intent)

                } else {
                    ErrorUsuario(this,"El usuario No esta disponible Verifique \n" + "que sea parte de la comunidad de UCN para poder entrar");
                }
            }
    }
    fun ErrorUsuario (context: Context, mensaje: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("ERROR")
        builder.setMessage(mensaje)

        // Botón de aceptar
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss() // Cierra el cuadro de diálogo
        }

        val dialog = builder.create()
        dialog.show()
    }
}