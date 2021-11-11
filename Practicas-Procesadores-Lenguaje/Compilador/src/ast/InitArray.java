package ast;

import java.util.ArrayList;

public class InitArray extends Nodo {
    public ArrayList<Nodo> exps;

    public InitArray(){
        this.exps = new ArrayList<Nodo>();
    }

    public InitArray(ArrayList<Nodo> exps){
        this.exps = exps;
    }

    public void addExp(ArrayList<Expresion> exps){
        for(int i = 0; i < exps.size(); ++i){
            this.exps.add(exps.get(i));
        }
    }

    public void add(Nodo e){
        exps.add(e);
    }

    public String toString(){
        String str = "{";
        int i = 0;
        
        while(i < exps.size() - 1){
            str += exps.get(i).toString() + ", ";
            ++i;
        }
        str += exps.get(i).toString() + "}";

        return str;
    }

    public void vincula(){ //por arrays de enums
        for (Nodo n : exps)
            n.vincula();
    }

    public void chequea(){ 
        Tipo t;
        boolean ok = true;
        exps.get(0).chequea(); //Suponer que el array que llega es de dimensiones correctas, pues si no da error sintactico.
        if(exps.get(0).tipoN == null){
            t = null;
        }
        else{
            t = exps.get(0).tipoN;
            if(exps.get(0) instanceof Expresion){
                t.nDim = 1;
                for(int i = 1; i < exps.size() && ok; ++i){
                    exps.get(i).chequea();
                    if(exps.get(i).tipoN == null || !(exps.get(i).tipoN.id).equals(t.id)){
                        ok = false;
                        System.out.println("ERROR: no coinciden los tipos de los elementos del array (expresion)");
                        t = null;
                    }
                }   
            }
            else{
               
                for(int i = 1; i < exps.size() && ok; ++i){
                    exps.get(i).chequea();
                    if(!(exps.get(i) instanceof Expresion)){
                        if(!((exps.get(i).tipoN.id).equals(t.id) && exps.get(i).tipoN.nDim == t.nDim)){
                            ok = false;
                            System.out.println("ERROR: no coinciden los tipos de los elementos del array");
                            System.out.println(exps.get(i) + " " + i + " Datos de tipo: " + exps.get(i).tipoN.id + " " + exps.get(i).tipoN.nDim);
                            System.out.println(exps.get(0) + " Datos de tipo: " + t.id + " " + t.nDim);
                            t = null;
                        }
                    }
                } 
                if(ok)
                    t.nDim += 1;
                
            } 
        }

        if(t == null){
            Programa.okChequea = false;
        }
        
        tipoN = t;
    }

    @Override
    public String generaCodigo() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void delta() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    }
}
