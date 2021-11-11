package errors;

import alex.UnidadLexica;
import alex.TokenValue;

public class GestionErroresTiny {
    public static int numErrores = 0;

    public void errorLexico(int fila, String lexema) {
      System.out.println("ERROR fila "+fila+": Caracter inesperado: "+lexema); 
      //System.exit(1);
    }  
    public void errorSintactico(UnidadLexica unidadLexica) {
      numErrores++;
      System.out.print("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado "+((TokenValue)unidadLexica.value).lexema+"\n");
      //System.exit(1);
    }
}