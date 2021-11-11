
package ast;

import java.util.ArrayList;

public class Bloque extends Nodo{
    public ArrayList<Instruccion> instrucciones;

    public Bloque(ArrayList<Instruccion> ins){
        this.instrucciones = ins;
    }

    public String toString(){
        String ins = "";
        int i = 0;

        while(i < instrucciones.size() - 1){
            ins += instrucciones.get(i).toString() + ";\n";
            ++i;
        }
        ins += instrucciones.get(i).toString() + ";";
        
        return ins;
    }

    public void vincula(){
        Programa.tablas.abreBloque(); 
        for(Instruccion i : instrucciones){
            i.vincula();
        }
        Programa.tablas.cierraBloque();
    }
    
    public void vincula(ArrayList<InsDec> parametros) {
        Programa.tablas.abreBloque(); 
        for(InsDec dec : parametros){
            dec.vincula();
        }

        for(Instruccion i : instrucciones){
            i.vincula();
        }
        Programa.tablas.cierraBloque();
    }
    
    public void chequea() {
        for(Instruccion i : instrucciones){
            i.chequea();
        }
    }

    public String generaCodigo() {
        String str = "";
        for (Instruccion i : instrucciones){
            str += i.generaCodigo();
        }
        return str;
    }

    public void delta() {
        for(int i = 0; i < instrucciones.size(); ++i){
            instrucciones.get(i).delta();
        }        
    }

    public void memoria() {
        for(Instruccion i: instrucciones){
            i.memoria();
        }
    }
}
