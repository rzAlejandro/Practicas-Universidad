package ast;


public class InsAsig extends Instruccion{
    public ParteIzq izq;
    public ParteDer der;
    
    public InsAsig(ParteIzq izq, ParteDer der){
        this.izq = izq;
        this.der = der;
    }

    public String toString(){
        return ("Asignacion(" + izq.toString() + " = " + der.toString() + ")");
    }

    public void vincula(){
        izq.vincula();
        der.vincula();
    }   

    public void chequea(){
        izq.chequea();
        der.chequea();
        
        if(izq.tipoN == null || der.tipoN == null){
            if(izq.tipoN == null)
                System.out.println("Izquierda es nulo");
            else
                System.out.println("Derecha es nulo");
            Programa.okChequea = false;
        }
        else{
            if(!(izq.tipoN.nDim == der.tipoN.nDim)){
                System.out.print("Mal las dimensiones: ");
                System.out.println(izq.tipoN.nDim + " , " + der.tipoN.nDim);
                Programa.okChequea = false;
            }
            else{
                if(!(izq.tipoN.id).equals(der.tipoN.id)){
                    System.out.print("Los tipos estan mal: ");
                    System.out.println(izq.tipoN.id + " , " + der.tipoN.id);
                    Programa.okChequea = false;
                }
            }
        }  
    }

    public String generaCodigo() {
        String str = "";
        str += izq.generaCodigo();
        str += der.generaCodigo();
        str += "i32.store\n";
        return str;
    }

    public void delta() {
        izq.delta();
    }

    @Override
    public void memoria() {
        // TODO Auto-generated method stub
        
    }
}
