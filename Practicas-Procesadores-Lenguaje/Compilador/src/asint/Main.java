package asint;

import java.io.*;
import alex.AnalizadorLexicoTiny;
import errors.GestionErroresTiny;
import ast.*;
//import asem.AnalizadorSemanticoTiny;

public class Main {
   public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
		
		Programa p = (Programa)asint.parse().value;
		if (GestionErroresTiny.numErrores == 0){
			System.out.println(p);
			p.vincula();
			if(Programa.okVincula){
				p.chequea();
				if(Programa.okChequea){
					p.delta();
					p.memoria();

					PrintWriter w = new PrintWriter("codigo.wat", "UTF-8");
					String codigo = p.generaCodigo();
					w.println(codigo);
					w.close();
				}
				else{
					System.out.println("ERROR EN TIPADO");
				}
			}
			else{
				System.out.println("ERROR EN VINCULACION");
			}
        }
	}
}   
   
