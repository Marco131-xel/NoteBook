package com.example.notebook.analizadorTexto;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import com.example.notebook.excepciones.Errores;

%%

// CODIGO USUARIO
%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

%cup
%class lexer
%public
%line
%char
%column
%full

// PATRONES
ITALIC=\*[^*]+\*
BOLD=\*\*[^*]+\*\*
BOLD_ITALIC=\*\*\*[^*]+\*\*\*
HEADER=\#{1,6} +[^\n#][^\n]*
LISTA=\+ .*
NUM_PUNTO=[0-9]+\.[^\n]*
PARRAFO=[a-zA-Z][^\n]*
BLANCOS=[\ \r\t\f\n]+
%%

<YYINITIAL> {BOLD_ITALIC} {return new Symbol(sym.BOLD_ITALIC, yyline, yycolumn,yytext());}
<YYINITIAL> {BOLD} {return new Symbol(sym.BOLD, yyline, yycolumn,yytext());}
<YYINITIAL> {ITALIC} {return new Symbol(sym.ITALIC, yyline, yycolumn,yytext());}
<YYINITIAL> {HEADER} {return new Symbol(sym.HEADER, yyline, yycolumn,yytext());}
<YYINITIAL> {LISTA} {return new Symbol(sym.LISTA, yyline, yycolumn,yytext());}
<YYINITIAL> {NUM_PUNTO} {return new Symbol(sym.NUM_PUNTO, yyline, yycolumn,yytext());}
<YYINITIAL> {PARRAFO} {return new Symbol(sym.PARRAFO, yyline, yycolumn,yytext());}
<YYINITIAL> {BLANCOS} {/*VACIO*/}
<YYINITIAL> . {
                System.out.println("Error lexico detectado: " + yytext() + " en linea " + yyline + " columna " + yycolumn);
                listaErrores.add(new Errores("LEXICO","El caracter "+
                yytext()+" No pertenece al lenguaje NoteBook", yyline, yycolumn));
}
