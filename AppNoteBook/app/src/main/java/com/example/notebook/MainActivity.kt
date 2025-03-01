package com.example.notebook

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.activity.ComponentActivity
import com.example.notebook.patroninterprete.Codigo
import com.example.notebook.patroninterprete.Texto
import ru.noties.jlatexmath.JLatexMathView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones Inferiores
        val btnTexto = findViewById<Button>(R.id.btnTexto)
        val btnCodigo = findViewById<Button>(R.id.btnCodigo)
        // Elemento de codigo
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val salCodigo = findViewById<TextView>(R.id.tvCodigoSalida)
        val compiCodigo = findViewById<Button>(R.id.btnEjecutarCodigo)
        val btnLimpiarCodigo = findViewById<Button>(R.id.btnLimpiarCodigo)
        val errorContainer = findViewById<LinearLayout>(R.id.errorContainer)
        val errorCodigo = findViewById<TextView>(R.id.tvCodigoErrores)
        val latexView = findViewById<JLatexMathView>(R.id.latexView)
        // Elementos de texto
        val etTexto = findViewById<EditText>(R.id.etTexto)
        val salTexto = findViewById<TextView>(R.id.tvTextoSalida)
        val compiTexto = findViewById<Button>(R.id.btnEjecutarTexto)
        val btnLimpiarTexto = findViewById<Button>(R.id.btnLimpiarTexto)
        val errorContaTexto = findViewById<LinearLayout>(R.id.errorContaTexto)
        val errorTexto = findViewById<TextView>(R.id.tvTextoErrores)
        // Contenedores
        val contenedorCodigo = findViewById<LinearLayout>(R.id.contenedorCodigo)
        val contenedorTexto = findViewById<LinearLayout>(R.id.contenedorTexto)
        // Mostrar la seccion de texto o codigo
        btnTexto.setOnClickListener {
            contenedorTexto.visibility = View.VISIBLE
            contenedorCodigo.visibility = View.GONE
        }

        btnCodigo.setOnClickListener {
            contenedorCodigo.visibility = View.VISIBLE
            contenedorTexto.visibility = View.GONE
        }
        // Boton compilar Texto
        var historialTexto = ""
        compiTexto.setOnClickListener{
            val texto = etTexto.text.toString()
            if (texto.isNotEmpty()){
                val (html, errores) = analizadorTexto(texto)

                if (errores.isNotEmpty()){
                    errorTexto.text = errores
                    errorContaTexto.visibility = View.VISIBLE
                } else {
                    etTexto.text.clear()
                    errorContaTexto.visibility = View.GONE
                }

                if (errores.isEmpty()) {
                    historialTexto += "<br>$html"
                    salTexto.text = Html.fromHtml(historialTexto, Html.FROM_HTML_MODE_COMPACT)
                    salTexto.visibility = View.VISIBLE
                }
            }
        }
        // Boton compilar Codigo
        compiCodigo.setOnClickListener {
            val codigo = etCodigo.text.toString()
            if (codigo.isNotEmpty()) {
                val (consola, errores, latex) = analizadorCodigo(codigo)

                if (errores.isNotEmpty()) {
                    errorCodigo.text = errores
                    errorContainer.visibility = View.VISIBLE
                } else {
                    etCodigo.text.clear()
                    errorContainer.visibility = View.GONE
                }

                if (errores.isEmpty()) {
                    val salidaAnterior = salCodigo.text.toString()
                    salCodigo.text = if (salidaAnterior.isEmpty()) consola else "$salidaAnterior\n$consola"
                    salCodigo.visibility = View.VISIBLE
                }

                if (latex.isNotEmpty()){
                    latexView.setLatex(latex)
                    findViewById<LinearLayout>(R.id.contenedorLatex).visibility = View.VISIBLE
                }
            }
        }
        // Boton limpiar codigo
        btnLimpiarCodigo.setOnClickListener {
            salCodigo.text = ""
            findViewById<LinearLayout>(R.id.contenedorLatex).visibility = View.GONE
        }
        // Boton limpiar texto
        btnLimpiarTexto.setOnClickListener{
            historialTexto = ""
            salTexto.text = ""
        }
    }

    // Metodo para analizar codigo
    private fun analizadorCodigo(codigo: String): Triple<String, String, String> {
        return try {
            val resultado = Codigo.ejecutar(codigo)
            Triple(resultado[0], resultado[1], resultado[2])
        } catch (e: Exception) {
            Triple("", "Error: ${e.message}", "")
        }
    }

    // Metodo para analizar texto
    private fun analizadorTexto(texto: String): Pair<String, String> {
        return try {
            val resultado = Texto.interpretar(texto)
            Pair(resultado[0], resultado[1])
        }catch (e: Exception) {
            Pair("", "Error: ${e.message}")
        }
    }
}
