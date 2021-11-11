package ast;


public class InsRet extends Instruccion{
    public Expresion valor;
    public Funcion referencia;

    public InsRet(Expresion valor){
        this.valor = valor;
    }

    public String toString(){
        return ("Return(" + valor.toString() + ")");
    }

    public void vincula(){
        valor.vincula();
        referencia = Programa.tablas.nodoFuncs.get(Programa.tablas.nodoFuncs.size() - 1); //cuando hay un return en el vincula es porque se esta haciendo el vincula de una funcion que lo usa
    }

    public void chequea(){
        if(referencia.tipo.tipo.id.equals("void")){
            System.out.println("ERROR: NO se puede hacer return en una funcion de tipo void");
            Programa.okChequea = false;
            
        }
        else {
            valor.chequea();
            if(valor.tipoN == null || !((valor.tipoN.id).equals(referencia.tipo.tipo.id) && valor.tipoN.nDim == referencia.tipo.tipo.nDim)){
                System.out.println("ERROR: hay que hacer un return del mismo tipo que la funcion");
                Programa.okChequea = false;
            }
        } 
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
