
package ast;

import java.util.ArrayList;

public class Funciones extends Nodo{
    public ArrayList<Funcion> listaFunciones;

    public Funciones(){
        listaFunciones = new ArrayList<Funcion>();
    }

    public void addF(Funcion f){
        listaFunciones.add(f);
    }

    public String toString(){
        String funcs = "";
        if(listaFunciones.size() > 0){
            int i = 0;
            
            while(i < listaFunciones.size() - 1){
                funcs += listaFunciones.get(i).toString() + "\n";
                ++i;
            }
            funcs += listaFunciones.get(i).toString();
        }

        return ("Funciones {\n" + funcs + "\n}");
    }

    public void vincula(){
        for(Funcion f : listaFunciones){
            f.vincula();
        }
    }

    public void chequea(){
        for(Funcion f : listaFunciones){
            f.chequea();
        }
    }

    @Override
    public String generaCodigo() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void delta() {
        for(Funcion f : listaFunciones){
            f.delta();
        }        
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    }
}