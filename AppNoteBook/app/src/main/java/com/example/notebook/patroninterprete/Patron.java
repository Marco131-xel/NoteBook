package com.example.notebook.patroninterprete;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.simbolo.*;
import com.example.notebook.analizadores.*;
import com.example.notebook.excepciones.Errores;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import java_cup.runtime.Symbol;

public class Patron {

    public static String ejecutar(String codigo) {
        try {
            Lexer l = new Lexer(new BufferedReader(new StringReader(codigo)));
            parser p = new parser(l);
            var resultado = p.parse();

            if (resultado == null || resultado.value == null) {
                return "Error: El análisis no generó un resultado válido.";
            }

            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new TablaSimbolos();
            tabla.setNombre("Global");
            ast.setConsola("");

            for (var a : ast.getInstrucciones()) {
                a.interpretar(ast, tabla);
            }
            return ast.getConsola();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
