package com.example.notebook.analizadorCodigo;

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
%class Lexer
%public
%line
%char
%column
%full

// TOKENS
POTENCIA="^"
MAS="+"
MENOS="-"
POR="*"
DIV="/"
PAR1="("
PAR2=")"
IGUAL="="
COMA=","
// PALABRAS RESERVADAS
PRINT="print"
FORMAT="format"
PLOT="plot"
// PATRONES
BLANCOS=[\ \r\t\f\n]+
ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
IDENTIFICADOR=[a-zA-Z][a-zA-Z0-9_]*
CADENA=[\"]([^\"])*[\"]
%%

// TOKENS
<YYINITIAL> {POTENCIA} {return new Symbol(sym.POTENCIA, yyline, yycolumn,yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {POR} {return new Symbol(sym.POR, yyline, yycolumn,yytext());}
<YYINITIAL> {DIV} {return new Symbol(sym.DIV, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {COMA} {return new Symbol(sym.COMA, yyline, yycolumn,yytext());}
// PALABRAS RESERVADAS
<YYINITIAL> {PRINT} {return new Symbol(sym.PRINT, yyline, yycolumn,yytext());}
<YYINITIAL> {FORMAT} {return new Symbol(sym.FORMAT, yyline, yycolumn,yytext());}
<YYINITIAL> {PLOT} {return new Symbol(sym.PLOT, yyline, yycolumn,yytext());}
// PATRONES
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {IDENTIFICADOR} {return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn,yytext());}
<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }
<YYINITIAL> {BLANCOS} {/*VACIO*/}
<YYINITIAL> . {
                System.out.println("Error lexico detectado: " + yytext() + " en linea " + yyline + " columna " + yycolumn);
                listaErrores.add(new Errores("LEXICO","El caracter "+
                yytext()+" No pertenece al lenguaje NoteBook", yyline, yycolumn));
}