package com.example.notebook.instrucciones;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.simbolo.*;

public class AsignacionODeclaracion extends Instruccion {

    private String id;
    private Instruccion exp;

    public AsignacionODeclaracion(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verificar si la variable ya existe en la tabla de símbolos
        var variable = tabla.getVariable(id);

        var valorInterpretado = exp.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        TipoDato nuevoTipo = getTipoDesdeValor(valorInterpretado);

        if (variable == null) {
            // La variable no existe declaracion
            Simbolo nuevoSimbolo = new Simbolo(new Tipo(nuevoTipo), id, valorInterpretado);
            boolean creada = tabla.setVariable(nuevoSimbolo);
            if (!creada) {
                return new Errores("SEMANTICO", "Error al declarar variable " + id, linea, col);
            }
        } else {
            // La variable existe asignación
            variable.setTipo(new Tipo(nuevoTipo));
            variable.setValor(valorInterpretado);
        }

        return null;
    }

    private TipoDato getTipoDesdeValor(Object valor) {
        if (valor instanceof Integer) return TipoDato.ENTERO;
        if (valor instanceof Double) return TipoDato.DECIMAL;
        if (valor instanceof String) return TipoDato.CADENA;
        return TipoDato.VOID;
    }
}
