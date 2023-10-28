package com.example.ucn_conectme

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class CondicionesUsoActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condiciones_uso)
        FirebaseApp.initializeApp(this)

        obtenerTerminosDeUso()
    }

    private fun obtenerTerminosDeUso() {
        val docRef = db.collection("CondicionesDeUso").document("CondicionesDeUso")

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val termsText = document.getString("Condiuso")
                    val textViewTerminosUso = findViewById<TextView>(R.id.textViewTerminosUso)
                    textViewTerminosUso.text = termsText
                } else {
                    // Documento no encontrado
                }
            }
            .addOnFailureListener { exception ->
                // Manejar el fallo
            }
    }
}
