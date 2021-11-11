package ast;

import java.util.ArrayList;

public class Estructura extends Nodo{
    public ArrayList<Expresion> acc; //Si es acceso a struct, son expresiones básicas con tipo NULL
    public ArrayList<Integer> aux; // 0-> atributo de struct; 1-> array

    public Estructura(){
        this.acc = new ArrayList<Expresion>();
        this.aux = new ArrayList<Integer>();
    }

    public String toString(){
        String str = "";
        int i = 0;
        
        while(i < acc.size()){
            if(aux.get(i) == 0){
                str += "." + acc.get(i).toString();
            }
            else{
                str += "[" + acc.get(i).toString() + "]";
            }
            ++i;
        }
        return str;
    }

    public void vincula(){
        for(int i = 0; i < aux.size(); ++i){
            if(aux.get(i) != 0){ //si es un atributo de un struct no se vincula, eso es comprobacion de tipos
                acc.get(i).vincula();
            }
        }
    }

    public void chequea(Tipo tipo){
        Tipo t = new Tipo(tipo.id, tipo.nDim, tipo.dims, tipo.tipoBasico, tipo.referencia);
        boolean stop = false;
        
        //System.out.println(acc.size());
        for(int i = 0; i < aux.size() && !stop; ++i){
            if(aux.get(i) != 0){ //Acceso a array
                //System.out.println(t.nDim);
                t.nDim--;
                acc.get(i).chequea(); //chequear si dimension es correcta
                if(!((acc.get(i).tipoN.id).equals("int") && acc.get(i).tipoN.nDim == 0)){
                    System.out.println("ERROR: la dimensión debe ser de tipo entero");
                    t = null;
                    stop = true;
                }
            }
            else{ //acceso a struct
                //System.out.println(acc.get(i));
                boolean stop2 = false;
                ArrayList<Struct> structs = Programa.tablas.getStructs(); //Para ver que struct le corresponde
                for(int j = 0; j < structs.size() && !stop2; ++j){
                    if((structs.get(j).id).equals(t.id)){ //Buscar el struct que le corresponde
                        Struct s = structs.get(j);
                        for(int k = 0; k < s.atributos.size() && !stop; ++k){
                            s.atributos.get(k).chequea();
                            if((s.atributos.get(k).id).equals(((ExpBasica)acc.get(i)).valor)){
                                stop2 = true;
                                Tipo aux = s.atributos.get(k).tipoN;
                                t = new Tipo(aux.id, aux.nDim, aux.dims, aux.tipoBasico, aux.referencia);
                            }     
                        }
                        if(!stop2){  //Está en el struct correcto, pero no haya ningún atributo con este nombre (acc[i])
                            System.out.println("ERROR: no existe ningún atributo en dicho struct con nombre: " + acc.get(i));
                            t = null;
                            stop = true;
                        }     
                    }
                }
            }
        }

        if(t == null){
            Programa.okChequea = false;
        }

        tipoN = t;
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