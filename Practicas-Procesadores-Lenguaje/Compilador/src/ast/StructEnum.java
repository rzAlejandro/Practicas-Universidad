package ast;

import java.util.ArrayList;

public class StructEnum extends Nodo{
    public ArrayList<Struct> listaStructs;
    public ArrayList<EnumClass> listaEnums;

    public StructEnum(){
        this.listaStructs = new ArrayList<Struct>();
        this.listaEnums = new ArrayList<EnumClass>();
    }

    public void addS(Struct s){
        listaStructs.add(s);
    }
    
    public void addE(EnumClass e){
        listaEnums.add(e);
    }

    public String toString(){
        String ls = "", le = "";

        if(listaStructs.size() > 0 && listaEnums.size() > 0){
            int i = 0, j = 0;

            while(i < listaStructs.size() - 1){
                ls += listaStructs.get(i).toString() + "\n";
                ++i;
            }
            ls += listaStructs.get(i).toString();

            while(j < listaEnums.size() - 1){
                le += listaEnums.get(j).toString() + "\n";
                ++j;
            }
            le += listaEnums.get(j).toString();
        }
        else if(listaStructs.size() > 0){
            int i = 0;

            while(i < listaStructs.size() - 1){
                ls += listaStructs.get(i).toString() + "\n";
                ++i;
            }
            ls += listaStructs.get(i).toString();

        }
        else if(listaEnums.size() > 0){
            int j = 0;

            while(j < listaEnums.size() - 1){
                le += listaEnums.get(j).toString() + "\n";
                ++j;
            }
            le += listaEnums.get(j).toString();

        }

        return ("Structs {\n" + ls + "\n}\n\nEnums {\n" + le + "\n}");
    }

    public void vincula(){
        for(Struct s : listaStructs){
            s.vincula();
        }

        for(EnumClass e : listaEnums){
            e.vincula();
        }
    }

    public void chequea(){
        for(Struct s : listaStructs){
            s.chequea();
        }

        for(EnumClass e : listaEnums){
            e.chequea();
        }
    }

    @Override
    public String generaCodigo() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void delta() {
        for(EnumClass en : listaEnums){
            en.delta();
        }

        int aux = Programa.deltaCont;
        for(Struct s : listaStructs){
            Programa.deltaCont = 0;
            s.delta();
        }       
        Programa.deltaCont = aux; 
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    }
}