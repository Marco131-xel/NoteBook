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
        arbol.Print(expresion.toString());
        return null;
    }

    @Override
    public String toString() {
        return expresion.toString();
    }
}