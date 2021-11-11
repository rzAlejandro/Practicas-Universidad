package ast;

import java.util.ArrayList;

public class Case extends Nodo{
    public Expresion cond;
    public Bloque bloque;

    public Case(Expresion cond, ArrayList<Instruccion> lins){
        this.cond = cond;
        this.bloque = new Bloque(lins);
    }

    public String toString(){
        return ("Cond: " + cond.toString() + ", [" + bloque.toString() + "]");
    }

    public void vincula(){
        cond.vincula();
        bloque.vincula();
    }

    public void chequea(Tipo tipo){
        cond.chequea();
        if(!(cond.tipoN.id.equals(tipo.id) && cond.tipoN.nDim == 0)){
            System.out.println("ERROR: el tipo del case es incompatible con el del switch");
            Programa.okChequea = false;
        }
        bloque.chequea();
    }

    public void chequea() {}

    public String generaCodigo() {
        return bloque.generaCodigo();
    }

    public void delta() {
        bloque.delta();        
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    } 
}
