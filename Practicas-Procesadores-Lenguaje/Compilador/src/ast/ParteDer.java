package ast;


public class ParteDer extends Nodo{
    public Expresion exp;
    public InitArray initArray;
    public boolean isArray;

    public ParteDer(Expresion exp){
        this.exp = exp;
        this.isArray = false;
    }

    public ParteDer(InitArray initArray){
        this.initArray = initArray;
        this.isArray = true;
    }

    public String toString(){
        if(isArray){
            return initArray.toString();
        }
        else{
            return exp.toString();
        }
    }

    public void vincula(){
        if(isArray)
            initArray.vincula();
        else exp.vincula();
    }

    public void chequea(){
        if(isArray){
            initArray.chequea();
            tipoN = initArray.tipoN;
        }
        else{
            exp.chequea();
            tipoN = exp.tipoN;
        }
    }

    public String generaCodigo() {
        String str = "";
        if(isArray){
            System.out.println("No generamos codigo de inicializaciones de arryas :)");
        }
        else{
            str += exp.generaCodigo();
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
