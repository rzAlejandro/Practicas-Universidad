package ast;

import java.util.*;

public class Acceso extends Expresion{
    public String id;
    public Estructura estructura;
    public boolean isEst;
    public Nodo referencia;

    public Acceso(String id, Estructura estructura){
        this.id = id;
        this.estructura = estructura;
        this.isEst = true;
    }

    public Acceso(String id){
        this.id = id;
        this.isEst = false;
    }

    public String toString(){
        if(isEst){
            return(id + estructura.toString());
        }
        else{
            return(id);
        }
    }

    public void vincula(){
        referencia = Programa.tablas.buscaId(id);
        if(isEst){ //caso de arrays o structs
            estructura.vincula();
        }
        else if(referencia == null){ //no hay ningnua variable con ese valor, pero habrá que ver si es un valor de algún enum
            ArrayList<EnumClass> enumerados = Programa.tablas.getEnums();
            boolean stop = false;
            for (int i = 0; i < enumerados.size() && !stop; ++i){
                for (int j = 0; j < enumerados.get(i).valores.size() && !stop; ++j){
                    if(enumerados.get(i).valores.get(j).equals(id)){
                        referencia = enumerados.get(i);
                        stop = true;
                    } 
                }
            }
        }
        if(referencia == null){
            System.out.println("ERROR: no se encontro id para vincular");
            Programa.okVincula = false;
        }
    }

    public void chequea(){
        Tipo t;

        if(referencia == null){
            System.out.println("ERROR: acceso de algo sin vincular");
            t = null;
        }
        else{
            if(!isEst){ //Variable unidimensional
                if(referencia instanceof InsDec){ //el acceso se hace a una variable declarada
                    ((InsDec)referencia).chequea();
                    t = ((InsDec)referencia).tipoN; //Es igual que iaugalar a referencia.tipo
                }
                else{ //se hace una asignacion a un "valor" de un tipo enum
                    t = new Tipo(((EnumClass)referencia).id, false);
                } 
            }
            else{ //una estructura siempre es un arrays o acceso a structs de algo declarado antes (no de una valor de enum)
                if(referencia instanceof InsDec){
                    estructura.chequea(((InsDec)referencia).tipo);
                    t = estructura.tipoN;
                }
                else{ //aqui si referencia no es declaracion es que no se ha podido vincular 
                    t = null;
                }
            }
        }

        if(t == null){
            Programa.okChequea = false;
        }

        tipoN = t;
    }

    public String generaCodigo() { //acceso en parte derecha
        String str = "";
        if(isEst){ //solo tratamos caso para arrays
            str += "get_local $localsStart\n";
            str += "i32.const " + referencia.etiqueta + "\n";
            for(int i  = 0; i < estructura.acc.size(); ++i){
                str += estructura.acc.get(i).generaCodigo() + "\n"; 
                int aux = 1;
                for(int j = i+1; j < estructura.acc.size(); ++j){
                    aux *= Integer.parseInt(((ExpBasica)referencia.tipoN.dims.get(j)).valor); 
                }
                str += "i32.const " + aux + "\n";
                str += "i32.mul\n";
                str += "i32.add\n";
            }
            str += "i32.const 4\n";
            str += "i32.mul\n";
            str += "i32.add\n";
            str += "i32.load\n";

            /*
            str += "get_local $localsStart\n";
            str += "i32.const " + referencia.etiqueta + "\n";
            str += estructura.acc.get(0).generaCodigo() + "\n";
            str += "i32.add\n";
            str += "i32.const 4\n";
            str += "i32.mul\n";
            str += "i32.add\n";
            str += "i32.load\n";
            */
        }
        else{
            str += "get_local $localsStart\n";
            str += "i32.const " + referencia.etiqueta + "\n";
            str += "i32.const 4\n";
            str += "i32.mul\n";
            str += "i32.add\n";
            str += "i32.load\n";
        }
        return str;
    }

    public String generaCodigoIni() { //acceso en parte izquierda
        String str = "";
        if(isEst){  //solo tratamos caso para arrays
            str += "get_local $localsStart\n";
            str += "i32.const " + referencia.etiqueta + "\n";
            for(int i  = 0; i < estructura.acc.size(); ++i){
                str += estructura.acc.get(i).generaCodigo() + "\n"; 
                int aux = 1;
                for(int j = i+1; j < estructura.acc.size(); ++j){
                    aux *= Integer.parseInt(((ExpBasica)referencia.tipoN.dims.get(j)).valor); 
                }
                str += "i32.const " + aux + "\n";
                str += "i32.mul\n";
                str += "i32.add\n";
            }
            str += "i32.const 4\n";
            str += "i32.mul\n";
            str += "i32.add\n";
        }
        else{
            str += "get_local $localsStart\n";
            str += "i32.const " + referencia.etiqueta + "\n";
            str += "i32.const 4\n";
            str += "i32.mul\n";
            str += "i32.add\n";
        }
        return str;
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
