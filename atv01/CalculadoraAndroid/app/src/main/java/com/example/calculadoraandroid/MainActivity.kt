package com.example.calculadoraandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botaoConversor = findViewById<Button>(R.id.btnConverter);
        val edtCelcius = findViewById<EditText>(R.id.edtCelcius);
        val txvResposta = findViewById<TextView>(R.id.txvResposta)

        botaoConversor.setOnClickListener {
            val valor = edtCelcius.text.toString().toDoubleOrNull();
            if(valor == null) {
                Toast.makeText(this, "Envie uma temperatura válida!",
                    Toast.LENGTH_SHORT).show();
            } else {
                var F = valor * 1.8 + 32;
                txvResposta.text = F.toString();
            }
        }
    }
}