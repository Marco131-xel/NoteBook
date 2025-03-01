package com.example.notebook.instrucciones;

import com.example.notebook.abstracto.Instruccion;
import com.example.notebook.excepciones.Errores;
import com.example.notebook.simbolo.*;
import java.text.DecimalFormat;

public class Plot extends Instruccion {

    private Instruccion expresion;
    private Instruccion inicio;
    private Instruccion fin;

    public Plot(Instruccion expresion, Instruccion inicio, Instruccion fin, int linea, int col) {
        super(null, linea, col);
        this.expresion = expresion;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Interpretar los valores de inicio y fin
        Object valInicio = inicio.interpretar(arbol, tabla);
        Object valFin = fin.interpretar(arbol, tabla);

        if (!(valInicio instanceof Number) || !(valFin instanceof Number)) {
            return new Errores("SEMANTICO", "Los valores de inicio y fin deben ser numeros", this.linea, this.col);
        }

        double inicioVal = ((Number) valInicio).doubleValue();
        double finVal = ((Number) valFin).doubleValue();

        if (inicioVal > finVal) {
            return new Errores("SEMANTICO", "El valor de inicio no puede ser mayor que el de fin", this.linea, this.col);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        double rango = Math.abs(finVal - inicioVal);
        double paso;

        if (inicioVal % 1 == 0 && finVal % 1 == 0) {
            paso = 1;
        } else if (rango <= 3) {
            paso = 0.2;
        } else if (rango <= 10) {
            paso = 0.25;
        } else {
            paso = 0.5;
        }
        arbol.Print("x\t\tf(x)");

        for (double x = inicioVal; x <= finVal; x += paso) {
            TablaSimbolos tablaTemporal = new TablaSimbolos(tabla);
            tablaTemporal.setVariable(new Simbolo(new Tipo(TipoDato.DECIMAL), "x", x));

            Object resultado;
            try {
                resultado = expresion.interpretar(arbol, tablaTemporal);
                if (resultado instanceof Errores ||
                        (resultado instanceof Number && ((Number) resultado).doubleValue() == Double.POSITIVE_INFINITY)) {
                    resultado = "EQ";
                }
            } catch (ArithmeticException e) {
                resultado = "EQ";
            }

            String xStr = df.format(x);
            String resultadoStr = (resultado instanceof Number) ? df.format(resultado) : resultado.toString();

            arbol.Print(xStr + "\t\t" + resultadoStr);
        }
        return null;
    }
}

