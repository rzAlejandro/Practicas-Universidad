package ast;

import java.util.ArrayList;

public class InsSwitch extends Instruccion {
    public Expresion exp;
    public ArrayList<Case> cases;   
    
    public InsSwitch(Expresion exp, ArrayList<Case> cases){
        this.exp = exp;
        this.cases = cases;
    }

    public String toString(){
        String cs = "";
        int i = 0;

        while(i < cases.size() - 1){
            cs += cases.get(i).toString() + ", ";
            ++i;
        }
        cs += cases.get(i).toString();

        return ("Switch(Cond: " + exp.toString() + ", Cases: (" + cs + ")");
    }

    public void vincula(){
        exp.vincula();
        Programa.tablas.abreBloque();
        for(Case c : cases){
            c.vincula();
        }
        Programa.tablas.cierraBloque();
    }

    public void chequea(){
        exp.chequea();
        if(exp.tipoN == null || !exp.tipoN.tipoBasico){
            System.out.println("ERROR: switch solo puede tener tipo basico");
            Programa.okChequea = false;
        }            
        else{
            for(Case c : cases){
                c.chequea(exp.tipoN);
            }
        }
    }


    public String generaCodigo() {
        String str = "";
        str += exp.generaCodigo();
        for(int i = 0; i < cases.size() + 1; ++i){
            str += "block\n";
        }
        str += "br_table";
        for(int i = 1; i < cases.size() + 1; ++i){
            str += " " + i;
        }
        str += "\nend\n";
        for(int i = cases.size() - 1; i >= 0; --i){
            str += cases.get(i).generaCodigo() + "br 0\n" + "end\n";
        }
        return str;
    }

    public void delta() {
        int aux = Programa.deltaCont;
        for(Case c : cases){
            c.delta();
            Programa.deltaCont = aux;  
        }
    }

    public void memoria() {
        for(Case c : cases){
            c.memoria();
        }
    }
}