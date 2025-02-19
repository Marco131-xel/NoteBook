package com.example.notebook.instrucciones;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.simbolo.*;

public class Print extends Instruccion {

    private Instruccion expresion;

    public Print(Instruccion expresion, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var resultado = this.expresion.interpretar(arbol, tabla);
        if (resultado instanceof Errores) {
            return resultado;
        }
        arbol.Print(resultado.toString());
        return null;
    }
}
