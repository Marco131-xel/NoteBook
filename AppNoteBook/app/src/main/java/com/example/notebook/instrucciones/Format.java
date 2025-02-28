package com.example.notebook.instrucciones;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.simbolo.*;

public class Format extends Instruccion {
    private Instruccion expresion;

    public Format(Instruccion expresion, int linea, int col) {
        super(null, linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        String latex = convertirALatex(expresion.toString());
        arbol.setLatexOutput(latex);
        return null;
    }

    @Override
    public String toString() {
        return expresion.toString();
    }

    private String convertirALatex(String expresion) {
        return expresion.replace("+", " + ")
                .replace("-", " - ")
                .replace("*", " \\times ")
                .replace("/", " \\div ")
                .replace("(", " \\left(")
                .replace(")", " \\right)")
                .replace("^", "^{");
    }
}