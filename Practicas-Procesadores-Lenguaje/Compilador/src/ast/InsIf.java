package ast;


public class InsIf extends Instruccion {
    public Expresion cond;
    public Bloque bloqueIf;
    public Bloque bloqueElse;
    public boolean isElse;

    public InsIf(Expresion cond, Bloque bloqueIf){
        this.cond = cond;
        this.bloqueIf = bloqueIf;
        this.isElse = false;
    }

    public InsIf(Expresion cond, Bloque bloqueIf, Bloque bloqueElse){
        this.cond = cond;
        this.bloqueIf = bloqueIf;
        this.bloqueElse = bloqueElse;
        this.isElse = true;
    }

    public String toString(){
        if(isElse){
            return ("If(Cond: " + cond.toString() + ", BloqueIf: (" + bloqueIf.toString() + "), BloqueElse: (" + bloqueElse.toString() + "))");
        }
        else{
            return ("If(Cond: " + cond.toString() + ", BloqueIf: (" + bloqueIf.toString() + "))");
        }
    }

    public void vincula(){
        cond.vincula();
        bloqueIf.vincula();
        if(isElse){
            bloqueElse.vincula();
        }
    }

    public void chequea(){
        cond.chequea();
        if(cond.tipoN == null || !((cond.tipoN.id).equals("bool") && cond.tipoN.nDim == 0)){
            System.out.println("ERROR: Se necesita condicion booleana en el if");
            Programa.okChequea = false;
            
        }
        bloqueIf.chequea();
        if(isElse){
            bloqueElse.chequea();
        }
    }

    public String generaCodigo() {
        String str = "";
        str += cond.generaCodigo();
        str += "if\n" +  bloqueIf.generaCodigo();
        if(isElse){
            str += "else\n" +  bloqueElse.generaCodigo();
        }
        str += "end\n";
        return str;
    }

    public void delta() {
        int aux = Programa.deltaCont;
        bloqueIf.delta();
        Programa.deltaCont = aux;        
        if(isElse){ 
            bloqueElse.delta();
            Programa.deltaCont = aux;  
        }
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    }
}