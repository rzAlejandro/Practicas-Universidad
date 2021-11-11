package ast;

import java.util.ArrayList;

public class Funcion extends Nodo{
    public TipoFunc tipo;
    public String id;
    public ArrayList<InsDec> parametros;
    public Bloque bloque;
    
    public Funcion(TipoFunc tipo, String id, ArrayList<InsDec> parametros, Bloque bloque){
        this.tipo = tipo;
        this.id = id;
        this.parametros = parametros;
        this.bloque = bloque;
    }

    public String toString(){
        String param = "";

        if(parametros.size() > 0){
            int i = 0;

            while(i < parametros.size() - 1){
                param += parametros.get(i).toString() + ", ";
                ++i;
            }
            param += parametros.get(i).toString();
        }

        return ("- Funcion(Tipo: " + tipo.toString() + ", Nombre: " + id + ", Params: (" + param + "), Ins: (" + bloque.toString() + "))");
    }

    public void vincula(){
        tipo.vincula();
        Programa.tablas.insertaId(id, this);
        Programa.tablas.nodoFuncs.add(this);
        bloque.vincula(parametros);
    }

    public void chequea(){
        bloque.chequea();
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