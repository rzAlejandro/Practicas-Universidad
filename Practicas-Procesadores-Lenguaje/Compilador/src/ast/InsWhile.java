package ast;


public class InsWhile extends Instruccion{
    public Expresion cond;
    public Bloque bloque;

    public InsWhile(Expresion cond, Bloque bloque){
        this.cond = cond;
        this.bloque = bloque;
    }

    public String toString(){
        return ("While(Cond: " + cond.toString() + ", BloqueW: (" + bloque.toString() + "))");
    }

    public void vincula(){
        cond.vincula();
        bloque.vincula();
    }

    public void chequea(){
        cond.chequea();
        if(cond.tipoN == null || !((cond.tipoN.id).equals("bool") && cond.tipoN.nDim == 0)){
            System.out.println("ERROR: Se necesita condicion booleana en el while");
            Programa.okChequea = false;
            
        }
        bloque.chequea();
    }

    public String generaCodigo() {
        String str = "";
        str += "block\n";
        str += "loop\n"; 
        str += cond.generaCodigo(); 
        str += "i32.eqz\n";
        str += "br_if 1\n";
        str += bloque.generaCodigo();
        str += "br 0\n";
        str += "end\n";
        str += "end\n";
        return str;
    }

    public void delta() {
        int aux = Programa.deltaCont;
        bloque.delta();
        Programa.deltaCont = aux;
    }

    public void memoria() {
        bloque.memoria();        
    }
}