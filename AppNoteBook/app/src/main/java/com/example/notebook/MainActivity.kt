package com.example.notebook

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.ComponentActivity
import com.example.notebook.patroninterprete.Codigo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTexto = findViewById<Button>(R.id.btnTexto)
        val btnCodigo = findViewById<Button>(R.id.btnCodigo)
        val btnReportes = findViewById<Button>(R.id.btnReportes)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val salCodigo = findViewById<TextView>(R.id.tvCodigoSalida)
        val compiCodigo = findViewById<Button>(R.id.btnEjecutarCodigo)
        val btnLimpiarCodigo = findViewById<Button>(R.id.btnLimpiarCodigo)
        val errorContainer = findViewById<LinearLayout>(R.id.errorContainer)
        val errorText = findViewById<TextView>(R.id.tvCodigoErrores)
        val contenedorCodigo = findViewById<LinearLayout>(R.id.contenedorCodigo)
        val contenedorTexto = findViewById<LinearLayout>(R.id.contenedorTexto)

        btnTexto.setOnClickListener {
            contenedorTexto.visibility = View.VISIBLE
            contenedorCodigo.visibility = View.GONE
        }

        btnCodigo.setOnClickListener {
            contenedorCodigo.visibility = View.VISIBLE
            contenedorTexto.visibility = View.GONE
        }


        compiCodigo.setOnClickListener {
            val codigo = etCodigo.text.toString()
            if (codigo.isNotEmpty()) {
                val (consola, errores) = analizadorCodigo(codigo)

                if (errores.isNotEmpty()) {
                    // Mostrar los errores y NO borrar el código
                    errorText.text = errores
                    errorContainer.visibility = View.VISIBLE
                } else {
                    // Si el código es correcto, limpiar la entrada y ocultar errores
                    etCodigo.text.clear()
                    errorContainer.visibility = View.GONE
                }

                // Si hay errores, NO aumentar el historial
                if (errores.isEmpty()) {
                    val salidaAnterior = salCodigo.text.toString()
                    salCodigo.text = if (salidaAnterior.isEmpty()) consola else "$salidaAnterior\n$consola"
                    salCodigo.visibility = View.VISIBLE
                }
            }
        }

        btnLimpiarCodigo.setOnClickListener {
            salCodigo.text = ""
        }
    }

    private fun analizadorCodigo(codigo: String): Pair<String, String> {
        return try {
            val resultado = Codigo.ejecutar(codigo)
            Pair(resultado[0], resultado[1]) // [0] = Consola, [1] = Errores
        } catch (e: Exception) {
            Pair("", "Error: ${e.message}")
        }
    }
}
