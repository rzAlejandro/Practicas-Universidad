package ast;

import java.util.ArrayList;

public class Tipo extends Nodo{
    public String id; //te guarda el tipo de la variable o array (basicos, struct o enum) y si es array, las dimensiones las controlamos por separado
    public boolean tipoBasico;
    public int nDim;
    public ArrayList<Expresion> dims; //si dims es vacío estamos hablando de un tipo de una variable y si no de un tipo de un array con sus dimensiones
    public Nodo referencia;

    public Tipo(String id, boolean tipoBasico){
        this.id = id;
        this.nDim = 0;
        this.dims = new ArrayList<Expresion>();
        this.tipoBasico = tipoBasico;
    }

    public Tipo(String id, int nDim, ArrayList<Expresion> dims, boolean tipoBasico){
        this.id = id;
        this.nDim = nDim;
        this.dims = dims;
        this.tipoBasico = tipoBasico;
    }

    public Tipo(String id, int nDim, ArrayList<Expresion> dims, boolean tipoBasico, Nodo referencia) {
        this.id = id;
        this.nDim = nDim;
        this.dims = dims;
        this.tipoBasico = tipoBasico;
        this.referencia = referencia;
    }

    public String toString(){
        String str = "";
        
        if(nDim > 0){
            int aux = nDim;

            while(aux > 0){
                str += "array(";
                --aux;
            }

            str += id + ", ";
            for(int i = 0; i < nDim - 1; ++i){
                str += dims.get(i).toString() + ", ";
            }
            str += dims.get(nDim - 1).toString();

            while(aux < nDim){
                str += ")";
                ++aux;;
            }

            return str;
        }
        else{
            str += id;
            return str;
        }
    }

    public void vincula(){
        if(!tipoBasico){ //para vincular los structs o enums
            referencia = Programa.tablas.buscaId(id);
        }
        for(Expresion e : dims){ //si nuestro es de una varible dims será vacío y no hace nada
            e.vincula();
        }
    }

    public void chequea() {}

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
