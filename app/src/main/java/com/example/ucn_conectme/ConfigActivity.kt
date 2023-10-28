package com.example.ucn_conectme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val AbrirCondiciones = findViewById<LinearLayout>(R.id.userCondicionesdeuso)


        AbrirCondiciones.setOnClickListener {
            val intent = Intent(this,CondicionesUsoActivity::class.java)
            startActivity(intent);
        }
    }
}