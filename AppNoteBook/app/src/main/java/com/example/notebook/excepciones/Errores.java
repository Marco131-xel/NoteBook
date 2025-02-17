package com.example.notebook.excepciones;

public class Errores {
    private String lexema;
    private int linea;
    private int columna;
    private String tipo;
    private String desc;

    public Errores(String lexema, int linea, int columna, String tipo, String desc) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.desc = desc;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Errores{" + "lexema=" + lexema + ", linea=" + linea + ", columna=" + columna + ", tipo=" + tipo + ", desc=" + desc + '}';
    }
}