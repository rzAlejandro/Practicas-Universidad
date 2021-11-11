package ast;


public abstract class Nodo {
    public Tipo tipoN;
    public int etiqueta;
    public abstract void vincula();
    public abstract void chequea();
    public abstract String generaCodigo();
    public abstract void delta();
    public abstract void memoria();
}
