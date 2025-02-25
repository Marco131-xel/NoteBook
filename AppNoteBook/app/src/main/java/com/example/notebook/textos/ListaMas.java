package com.example.notebook.textos;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.simbolo.*;

public class ListaMas extends Instruccion {

    private Instruccion titulo;

    public ListaMas(Instruccion titulo, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.titulo = titulo;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var resultado = this.titulo.interpretar(arbol, tabla);
        if (resultado instanceof Errores) {
            return resultado;
        }
        arbol.Print(resultado.toString());
        return null;
    }
}
