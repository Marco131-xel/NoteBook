package com.example.notebook.reportes;

public class Ocurrencia {
    String operador;
    int  linea;
    int columna;
    String expresion;

    public Ocurrencia(String operador, int linea, int columna, String expresion) {
        this.operador = operador;
        this.linea = linea;
        this.columna = columna;
        this.expresion = expresion;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
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

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

}
