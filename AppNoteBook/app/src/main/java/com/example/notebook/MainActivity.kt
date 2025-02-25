package com.example.notebook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ViewFlipper
import androidx.activity.ComponentActivity
import com.example.notebook.patroninterprete.Codigo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTexto = findViewById<Button>(R.id.btnTexto)
        val btnCodigo = findViewById<Button>(R.id.btnCodigo)
        val btnReportes = findViewById<Button>(R.id.btnReportes)
        val panelito = findViewById<ViewFlipper>(R.id.viewFlipper)
        val etTexto = findViewById<EditText>(R.id.etTexto)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)

        btnTexto.setOnClickListener{

            panelito.displayedChild = 0
        }

        btnCodigo.setOnClickListener{
            panelito.displayedChild = 1
        }

        btnReportes.setOnClickListener{

        }

        /*compilar.setOnClickListener {
            val codigo = inputexto.text.toString()
            val (consola, errores) = ejecutarAnalizador(codigo)

            panelito.text = consola
            erroresView.text = if (errores.isEmpty()) "Sin errores" else errores
        }*/
    }

    private fun analizadorCodigo(codigo: String): Pair<String, String> {
        return try {
            val resultado = Codigo.ejecutar(codigo)
            Pair(resultado[0], resultado[1])
        } catch (e: Exception) {
            Pair("", "Error: ${e.message}")
        }
    }

}
