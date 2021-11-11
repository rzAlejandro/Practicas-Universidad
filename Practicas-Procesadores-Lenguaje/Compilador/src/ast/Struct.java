package ast;

import java.util.ArrayList;

public class Struct extends Nodo{
    public String id;
    public ArrayList<InsDec> atributos;

    public Struct(String id, ArrayList<InsDec> atributos){
        this.id = id;
        this.atributos = atributos; 
    }

    public String toString(){
        String atrs = "";
        int i = 0;

        while(i < atributos.size() - 1){
            atrs += atributos.get(i).toString() + ", ";
            ++i;
        }
        atrs += atributos.get(i).toString();

        return ("- Struct(Nombre: " + id + ", Atributos: (" + atrs + "))");
    }

    public void vincula(){
        Programa.tablas.insertaId(id, this); 
        for(InsDec d : atributos){
            d.vinculaStruct();// Puede ser que alguno de esos atributos sea un struct, enum o arrays de alguno de ellos.
        }
    }

    public void chequea(){
        for(InsDec dec : atributos){
            dec.chequea();
        }
    }

    @Override
    public String generaCodigo() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void delta() {
        for(InsDec dec : atributos){
            dec.delta();
        }
        
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    }
}