package ast;


public class ExpBasica extends Expresion{
    public String tipo;
    public String valor;

    public ExpBasica(String tipo, String valor){
        this.tipo = tipo;
        this.valor = valor;
    }

    public String toString(){
        return valor;
    }

    public void vincula(){}

    public void chequea(){
        tipoN = new Tipo(tipo, true);
    }

    public String generaCodigo() {
        String str = "";
        if(tipo.equals("int")){
            str += "i32.const " + valor;
        }
        else if(tipo.equals("bool")){
            if(valor.equals("true"))
                str += "i32.const 1";
            else str += "i32.const 0";
        }
        str += "\n";
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
