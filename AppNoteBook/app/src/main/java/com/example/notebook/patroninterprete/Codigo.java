package com.example.notebook.patroninterprete;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.analizadorCodigo.*;
import com.example.notebook.simbolo.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;

public class Codigo {

    public static String[] ejecutar(String codigo) {
        try {
            Lexer l = new Lexer(new BufferedReader(new StringReader(codigo)));
            parser p = new parser(l);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new TablaSimbolos();
            tabla.setNombre("Global");
            ast.setConsola("");
            LinkedList<Errores> lista = new LinkedList<>();
            lista.addAll(l.listaErrores);
            lista.addAll(p.listaErrores);

            for (var a : ast.getInstrucciones()) {
                if (a == null) {
                    continue;
                }

                var res = a.interpretar(ast, tabla);
                if (res instanceof Errores) {
                    lista.add((Errores) res);
                }
            }

            StringBuilder erroresStr = new StringBuilder();
            for (var i : lista) {
                erroresStr.append(i.toString()).append("\n");
            }

            return new String[]{ast.getConsola(), erroresStr.toString()};

        } catch (Exception e) {
            return new String[]{"", "Error: " + e.getMessage()};
        }
    }
}
