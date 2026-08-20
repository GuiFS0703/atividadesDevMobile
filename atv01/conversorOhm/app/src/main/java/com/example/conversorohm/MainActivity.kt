package com.example.conversorohm

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

            val etTensao = findViewById<EditText>(R.id.edtTensao)
            val etResistencia = findViewById<EditText>(R.id.edtResistencia)
            val etCorrente = findViewById<EditText>(R.id.edtCorrente)
            val btnCalcular = findViewById<Button>(R.id.btnConverter)
            val txvResultado = findViewById<TextView>(R.id.txvResposta)

            btnCalcular.setOnClickListener {
                val tensao = etTensao.text.toString()
                val resistencia = etResistencia.text.toString()
                val corrente = etCorrente.text.toString()

                val preenchidos = listOf(tensao, resistencia, corrente)
                    .filter { it.trim().isNotEmpty() }
                // listOf converte para uma List e IT é o elemento a ser convertido. Além disso, ele só conta se nao for nulo

                if (tensao.trim().isEmpty() && resistencia.trim().isEmpty() && corrente.trim().isEmpty()) {
                    Toast.makeText(this, "Preencha pelo menos 2 campos!", Toast.LENGTH_SHORT).show()
                }

                try {
                    val dTensao = tensao.toDoubleOrNull()
                    val dResistencia = resistencia.toDoubleOrNull()
                    val dCorrente = corrente.toDoubleOrNull()

                    when {
                        dTensao != null && dResistencia != null -> {
                            val corrente = dTensao / dResistencia
                            txvResultado.text = "Corrente : " + corrente.toString();
                        }

                        dTensao != null && dCorrente != null -> {
                            val resistencia = dTensao / dCorrente
                            txvResultado.text = "Resistencia: " + resistencia.toString();
                        }

                        dResistencia != null && dCorrente != null -> {
                            val tensao = dResistencia * dCorrente
                            txvResultado.text = "tensão: " + tensao.toString();
                        }

                        else -> {
                            Toast.makeText(this, "Combinação inválida!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Algo deu errado: ", Toast.LENGTH_SHORT).show()
                }
            }
        }
}