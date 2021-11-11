package ast;

import java.util.ArrayList;

public class ExpFunc extends Expresion {
    public String func; //nombre de la funcion
    public ArrayList<Expresion> parametros;
    public ArrayList<Funcion> referencias;
    
    public ExpFunc(String func, ArrayList<Expresion> parametros){
        this.func = func;
        this.parametros = parametros;
        this.referencias = new ArrayList<Funcion>();
    }

    public String toString(){
        String param = "";
        int i = 0;
        
        while(i < parametros.size() - 1){
            param += parametros.get(i).toString() + ",";
            ++i;
        }
        param += parametros.get(i).toString() + ")";

        return (func + "(" + param + ")");
    }

    public void vincula(){
        ArrayList<Funcion> funcs = Programa.tablas.getFuncs();
        for(Funcion f : funcs){
            if(f.id.equals(this.func)){
                referencias.add(f);
            }
        }
        if(referencias.isEmpty()){
            System.out.println("Funcion no definida");
            Programa.okVincula = false;
        }
        
        for(Expresion e : parametros){
            e.vincula();
        }
    }

    public void chequea(){
        Tipo t = null;
        boolean find = false;

        for(int i = 0; i < referencias.size() && !find; ++i){
            if(referencias.get(i).parametros.size() == this.parametros.size()){
                find = true; //tenemos la funcion con la que esta asociada
                t = referencias.get(i).tipo.tipo;
                boolean stop = false;

                for(int j = 0; j < parametros.size() && !stop; ++j){
                    parametros.get(j).chequea();
                    if(!((parametros.get(j).tipoN.id).equals(referencias.get(i).parametros.get(j).tipoN.id)
                      || (parametros.get(j).tipoN.nDim) == (referencias.get(i).parametros.get(j).tipoN.nDim))){
                        System.out.println("ERROR: tipo de parametros inconrrectos");
                        stop = true;
                        t = null;
                    };
                }
            }
        } 
        if(!find){
            System.out.println("ERROR: Esa funcion no existe, lo siento chaval");
            t = null;
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