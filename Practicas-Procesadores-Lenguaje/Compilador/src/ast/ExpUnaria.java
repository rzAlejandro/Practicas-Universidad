package ast;


public class ExpUnaria extends Expresion{
    public String oper;
    public Expresion op;

    public ExpUnaria(String oper, Expresion op){
        this.oper = oper;
        this.op = op;
    }
    public String toString(){
        return (oper + " " + op.toString());
    }

    public void vincula(){
        op.vincula();
    }

    public void chequea(){  //unarias solo hay el not y el -
        op.chequea();
        Tipo t = null;
        if(oper.equals("!")){
            if(op.tipoN == null || !((op.tipoN.id).equals("bool") && op.tipoN.nDim == 0)){
                System.out.println("ERROR: la expresión debe ser de op.tipoN bool");
                t = null;
            }
            else{
                t = op.tipoN;
            }
        }
        else{
            if(!((op.tipoN.id).equals("int") && op.tipoN.nDim == 0)){
                System.out.println("ERROR: la expresión debe ser de op.tipoN entero");
                t = null;
            }
            else{
                t = op.tipoN;
            }
        }

        if(t == null){
            Programa.okChequea = false;
        }
        
        tipoN = t;
    }
   
    public String generaCodigo() {
        String str = "";
        str += op.generaCodigo();
        if(oper.equals("!")){
            str += "i32.eqz\n";
        }
        else if (oper.equals("!")){ //-x
            str += "i32.const -1\n";
            str += "i32.mul\n";
        }
        else if (oper.equals("++")){ //++x o x++
            str += "i32.const 1\n";
            str += "i32.add\n";
        }
        else if (oper.equals("--")){ //--x o x--
            str += "i32.const 1\n";
            str += "i32.sub\n";
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
