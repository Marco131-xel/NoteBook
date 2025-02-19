package com.example.notebook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.notebook.patroninterprete.Patron

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputexto = findViewById<EditText>(R.id.texto)
        val compilar = findViewById<Button>(R.id.BT_ejecutar)
        val panelito = findViewById<TextView>(R.id.panelito)

        compilar.setOnClickListener {
            val codigo = inputexto.text.toString()
            val resultado = ejecutarAnalizador(codigo)
            panelito.text = resultado
        }

    }

    private fun ejecutarAnalizador(codigo: String): String {
        return try {
            Patron.ejecutar(codigo)
        } catch (e: Exception){
            "Error: ${e.message}"
        }
    }
}
