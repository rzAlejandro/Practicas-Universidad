package alex;

import asint.ClaseLexica;

public class ALexOperations {
   private AnalizadorLexicoTiny alex;
   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;
   }
   
   public UnidadLexica unidadMain() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAIN, "Main"); 
   } 

   public UnidadLexica unidadId() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN, alex.lexema()); 
   } 

   public UnidadLexica unidadEnt() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT, alex.lexema()); 
   } 

   public UnidadLexica unidadCaracter() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CARACTER, alex.lexema()); 
   } 

   public UnidadLexica unidadCadena() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema()); 
   } 

   public UnidadLexica unidadWhile() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "while"); 
   } 

   public UnidadLexica unidadIf() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "if"); 
   } 

   public UnidadLexica unidadElse() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "else"); 
   } 

   public UnidadLexica unidadEnd() {
      return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.END, "end"); 
   }

   public UnidadLexica unidadPrint() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PRINT, "print"); 
   } 

   public UnidadLexica unidadSwitch() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SWITCH, "switch"); 
   } 

   public UnidadLexica unidadCase() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CASE, "case"); 
   } 

   public UnidadLexica unidadStruct() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT, "struct"); 
   } 

   public UnidadLexica unidadEnum() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENUM, "enum"); 
   } 

   public UnidadLexica unidadBartolo() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BARTOLO, "bartolo"); 
   } 

   public UnidadLexica unidadReturn() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RETURN, "return"); 
   } 

   public UnidadLexica unidadInt() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT, "int"); 
   } 

   public UnidadLexica unidadVoid() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VOID, "void"); 
   } 

   public UnidadLexica unidadChar() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CHAR, "char"); 
   } 

   public UnidadLexica unidadString() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING, "String"); 
   } 

   public UnidadLexica unidadBool() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "bool"); 
   } 

   public UnidadLexica unidadTrue() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "true"); 
   } 

   public UnidadLexica unidadFalse() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "false"); 
   } 

   public UnidadLexica unidadParentesisApertura() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP, "("); 
   } 

   public UnidadLexica unidadParentesisCierre() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, ")"); 
   } 

   public UnidadLexica unidadLlaveApertura() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LAP, "{"); 
   } 

   public UnidadLexica unidadLlaveCierre() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LCIERRE, "}"); 
   } 

   public UnidadLexica unidadCorcheteApertura() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAP, "["); 
   } 

   public UnidadLexica unidadCorcheteCierre() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CCIERRE, "]"); 
   } 

   public UnidadLexica unidadIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "="); 
   } 

   public UnidadLexica unidadPunto() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, "."); 
   } 

   public UnidadLexica unidadComa() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ","); 
   } 

   public UnidadLexica unidadPuntoYComa() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA, ";"); 
   } 

   public UnidadLexica unidadDosPuntos(){
      return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DOSPUNTOS, ":"); 
   }

   public UnidadLexica unidadMasMas() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MASMAS, "++"); 
   } 

   public UnidadLexica unidadMenosMenos() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOSMENOS, "--"); 
   } 

   public UnidadLexica unidadSuma() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+"); 
   } 

   public UnidadLexica unidadResta() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-"); 
   } 

   public UnidadLexica unidadMul() {
   return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR, "*"); 
   } 
   public UnidadLexica unidadDiv() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "/"); 
   } 

   public UnidadLexica unidadModulo() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD, "%"); 
   } 
   
   public UnidadLexica unidadAnd() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "&&"); 
   } 

   public UnidadLexica unidadOr() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "||"); 
   } 

   public UnidadLexica unidadNot() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "!"); 
   } 

   public UnidadLexica unidadDesigual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DESIG, "!="); 
   } 

   public UnidadLexica unidadIgualIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGIG, "=="); 
   } 

   public UnidadLexica unidadMayorIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYIG, ">="); 
   } 

   public UnidadLexica unidadMayor() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAY, ">"); 
   }

   public UnidadLexica unidadMenorIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENIG, "<="); 
   } 

   public UnidadLexica unidadMenor() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MEN, "<"); 
   } 

   public UnidadLexica unidadEof() {
      return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF, "EOF"); 
   }
}

