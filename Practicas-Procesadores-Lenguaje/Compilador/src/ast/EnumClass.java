package ast;

import java.util.ArrayList;

public class EnumClass extends Nodo{
    public String id;
    public ArrayList<String> valores;

    public EnumClass(String id, ArrayList<String> valores){
        this.id = id;
        this.valores = valores;
    }

    public String toString(){
        String vals = "";
        int i = 0;

        while(i < valores.size() - 1){
            vals += valores.get(i) + ", ";
            ++i;
        }
        vals += valores.get(i);

        return ("- Enum(Nombre: " + id + ", Valores: (" + vals + "))");
    }

    public void vincula(){
        Programa.tablas.insertaId(id, this);
    }

    public void chequea(){}

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