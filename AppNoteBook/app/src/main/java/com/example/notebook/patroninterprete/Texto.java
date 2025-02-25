package com.example.notebook.patroninterprete;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.analizadorTexto.*;
import com.example.notebook.simbolo.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
// importaciones MARKDOWN
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.ast.Node;

public class Texto {

    public static String[] interpretar(String codigo) {
        try {
            lexer l = new lexer(new BufferedReader(new StringReader(codigo)));
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

            // Construimos el string de errores
            StringBuilder erroresStr = new StringBuilder();
            for (var i : lista) {
                erroresStr.append(i.toString()).append("\n");
            }

            // Convertimos el texto de consola (Markdown) a HTML
            String markdownContent = ast.getConsola();
            String htmlContent = convertirMarkdown(markdownContent);

            // Devolvemos tanto el HTML como los errores
            return new String[]{htmlContent, erroresStr.toString()};


        } catch (Exception e) {
            return new String[]{"", "Error: " + e.getMessage()};
        }
    }



    // Metodo para convertir el contenido MARKDOWN
    private static String convertirMarkdown(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
