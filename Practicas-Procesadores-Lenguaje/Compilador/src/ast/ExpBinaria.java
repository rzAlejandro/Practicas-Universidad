package ast;


public class ExpBinaria extends Expresion{
    public Expresion op1;
    public String oper;
    public Expresion op2;

    public ExpBinaria(Expresion op1, String oper, Expresion op2){
        this.op1 = op1;
        this.oper = oper;
        this.op2 = op2;
    }
    public String toString(){
        return (op1.toString() + " " + oper + " " + op2.toString());
    }
    
    public void vincula(){
        op1.vincula();
        op2.vincula();
    }

    public void chequea(){
        Tipo t = null;
        op1.chequea();
        op2.chequea();

        if(op1.tipoN == null || op2.tipoN == null){
            System.out.println("ERROR: expresiones con tipo incorrecto");
            t = null;
        }
        else{
            if(!((op1.tipoN.id).equals(op2.tipoN.id) && op1.tipoN.nDim == op2.tipoN.nDim)){
                System.out.println("ERROR: las dos expresiones no tienen el mismo tipo");
                t = null;
            }
            else{
                if(oper.equals("==") || oper.equals("!=")){
                    t = new Tipo("bool", true);
                }
                else if(oper.equals("<=") || oper.equals(">=") || oper.equals("<") || oper.equals(">")){
                    if(op1.tipoN.id.equals("int")){
                        t = new Tipo("bool", true);
                    }
                    else{
                        System.out.println("ERROR: la expresion ha de ser entera");
                        t = null;
                    }
                }
                else if(oper.equals("&&") || oper.equals("||")){
                    if(op1.tipoN.id.equals("bool")){
                        t = op1.tipoN;
                    }
                    else{
                        System.out.println("ERROR: la expresion ha de ser booleana");
                        t = null;
                    }
                }
                else{ //ops aritmeticas
                    if((op1.tipoN.id).equals("int")){
                        t = op1.tipoN;
                    }
                    else{ 
                        System.out.println("ERROR: la expresión ha de ser entera");
                        t = null;
                    }
                }
            }
        }

        if(t == null){
            Programa.okChequea = false;
        }

        tipoN = t;
    }
   
    public String generaCodigo() {
        String str = "";
        str += op1.generaCodigo();
        str += op2.generaCodigo();
        switch(oper){
            case "+" : str += "i32.add\n"; break;
            case "-" : str += "i32.sub\n"; break;
            case "*" : str += "i32.mul\n"; break;
            case "/" : str += "i32.div_s\n"; break;
            case "%" : str += "i32.rem_s\n"; break;
            case "&&" : str += "i32.and\n"; break;
            case "||" : str += "i32.or\n"; break;
            case "==" : str += "i32.eq\n"; break;
            case "!=" : str += "i32.ne\n"; break;
            case ">=" : str += "i32.ge_s\n"; break;
            case "<=" : str += "i32.le_s\n"; break;
            case ">" : str += "i32.gt_s\n"; break;
            case "<" : str += "i32.lt_s\n"; break;
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