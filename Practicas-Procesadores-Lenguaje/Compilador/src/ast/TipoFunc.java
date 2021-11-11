package ast;


public class TipoFunc extends Nodo{
    public Tipo tipo;
    public boolean isVoid; //para ver si es bool o no (si es bool tipo es null)

    public TipoFunc(Tipo tipo, boolean isVoid){
        this.tipo = tipo;
        this.isVoid = isVoid;
    }

    public String toString(){
        if(isVoid){
            return "void";
        }
        else{
            return tipo.toString();
        }
    }

    public void vincula(){
        if(!isVoid)
            tipo.vincula();
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
