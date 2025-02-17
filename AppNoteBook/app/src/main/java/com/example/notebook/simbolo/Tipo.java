package com.example.notebook.simbolo;

public class Tipo {
    private TipoDato tipo;

    public Tipo(TipoDato tipo){
        this.tipo = tipo;
    }

    public TipoDato getTipo() {
        return tipo;
    }

    public void setTipo(TipoDato tipo) {
        this.tipo = tipo;
    }
}
