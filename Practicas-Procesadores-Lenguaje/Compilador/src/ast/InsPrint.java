package ast;


public class InsPrint extends Instruccion {
    public Expresion exp;

    public InsPrint(Expresion exp){
        this.exp = exp;
    }

    public String toString(){
        return ("Print( " + exp.toString() + " )");
    }

    public void vincula(){
        exp.vincula();
    }

    public void chequea(){
        exp.chequea();
        if(exp.tipoN == null || !((exp.tipoN.id).equals("int") || (exp.tipoN.id).equals("char") ||
          (exp.tipoN.id).equals("String") || (exp.tipoN.id).equals("bool")) || exp.tipoN.nDim != 0){
            System.out.println("ERROR: No se puede hacer print de un tipo no basico");
            Programa.okChequea = false;
            
        }
    }

    @Override
    public String generaCodigo() {
        String str = "";
        str += exp.generaCodigo();
        str += "call $print\n";
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
