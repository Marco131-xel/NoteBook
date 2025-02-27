package com.example.notebook.reportes;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.simbolo.*;
import java.util.LinkedList;

public class RepoErrores extends Instruccion {

    public RepoErrores(int linea, int col) {
        super(null, linea, col);
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        LinkedList<Errores> lista = Arbol.getHistorialErrores();

        StringBuilder erroresStr = new StringBuilder();
        if (!lista.isEmpty()) {
            erroresStr.append("Tipo | Descripción | Línea | Columna\n");
            erroresStr.append("--------------------------------------\n");
            for (var i : lista) {
                erroresStr.append(i.getTipo()).append(" | ")
                        .append(i.getDesc()).append(" | ")
                        .append(i.getLinea()).append(" | ")
                        .append(i.getColumna()).append("\n");
            }
            arbol.Print(erroresStr.toString());
        } else {
            arbol.Print("-- No se encontraron errores --");
        }
        return null;
    }

}