package com.example.notebook.reportes;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.analizadorCodigo.*;
import com.example.notebook.simbolo.*;
import java.util.List;

public class RepoOcurrencias extends Instruccion {

    private parser parser;

    public RepoOcurrencias(parser parser, int linea, int col) {
        super(null, linea, col);
        this.parser = parser;
    }

    public void setParser(parser parser) {
        this.parser = parser;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (parser == null) {
            arbol.Print("-- Error: parser es null --");
            return null;
        }

        List<Ocurrencia> historial = parser.getHistorialOcurrencias();
        StringBuilder ocurrenciaStr = new StringBuilder();
        if (!historial.isEmpty()) {
            ocurrenciaStr.append("Operador | Linea | Columna | Ocurrencia\n");
            ocurrenciaStr.append("---------------------------------------\n");
            for (var i : historial) {
                ocurrenciaStr.append(i.getOperador()).append(" | ")
                        .append(i.getLinea()).append(" | ")
                        .append(i.getColumna()).append(" | ")
                        .append(i.getExpresion()).append("\n");
            }
        } else {
            ocurrenciaStr.append("-- No hay Ocurrencias --\n");
        }

        arbol.Print(ocurrenciaStr.toString());
        return null;
    }

}