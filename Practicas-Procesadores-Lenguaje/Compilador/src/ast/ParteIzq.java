package ast;

public class ParteIzq extends Nodo{
    public InsDec dec;
    public Acceso acc;
    public boolean isDec; 

    public ParteIzq(InsDec dec){
        this.dec = dec;
        this.isDec = true;
    }

    public ParteIzq(Acceso acc){
        this.acc = acc;
        this.isDec = false;
    }

    public String toString(){
        if(isDec){
            return dec.toString();
        }
        else{
            return acc.toString();
        }
    }

    public void vincula(){
        if(isDec)
            dec.vincula();
        else
            acc.vincula();
    }

    public void chequea(){
        if(isDec){
            dec.chequea();
            tipoN = dec.tipoN;
        }
        else{
            acc.chequea();
            tipoN = acc.tipoN;
        }
    }

    public String generaCodigo() {
        String str = "";
        if(isDec){
            str += dec.generaCodigoIni();
        }
        else{
            str += acc.generaCodigoIni();
        }
        return str;
    }

    public void delta() {
        if(isDec) dec.delta();
    }

    public void memoria() {
        if(isDec) dec.memoria();
        
    }
}
