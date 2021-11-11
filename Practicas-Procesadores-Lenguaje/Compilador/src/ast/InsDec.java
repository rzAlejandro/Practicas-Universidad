package ast;


public class InsDec extends Instruccion{
    public Tipo tipo;
    public String id;

    public InsDec(Tipo tipo, String id){
        this.tipo = tipo; 
        this.id = id;
    }

    public String toString(){ //solo se hará este cuando la declaración sea sin inicializar
        return ("Declaracion(Tipo: " + tipo.toString() + ", Nombre: " + id + ")");
    }

    public void vincula(){
        tipo.vincula();
        Programa.tablas.insertaId(id, this);
    }

    public void vinculaStruct(){
        tipo.vincula();
    }
    public void chequea(){
        tipoN = tipo; //devuelve el tipo de la declaracion
    }

    public String generaCodigo() {
        // TODO Auto-generated method stub
        return "";
    }

    public String generaCodigoIni() {
        String str = "";
        if(tipo.nDim == 0){
            str += "get_local $localsStart\n";
            str += "i32.const " + this.etiqueta + "\n";
            str += "i32.const 4\n";
            str += "i32.mul\n";
            str += "i32.add\n";
        }
        else{
            System.out.println("No generamos codigo de inicializaciones de arryas :)");
        }
        return str;
    }

    public void delta() {
        this.etiqueta = Programa.deltaCont;
        if(tipo.nDim == 0){ //tipo basico o struct
            Programa.deltaCont++;
        }       
        else{ //array
            int aux = 1;
            for(int i = 0; i < tipo.nDim; ++i){
                aux *= Integer.parseInt(((ExpBasica)tipo.dims.get(i)).valor);
            }
            Programa.deltaCont += aux;
        }
    }

    public void memoria() {
        if(tipo.nDim == 0){ //tipo basico o struct
            MainFuncion.maxMem = Math.max(MainFuncion.maxMem, this.etiqueta);
        }       
        else{ //array
            int aux = 1;
            for(int i = 0; i < tipo.nDim; ++i){
                aux *= Integer.parseInt(((ExpBasica)tipo.dims.get(i)).valor);
            }
            MainFuncion.maxMem = Math.max(MainFuncion.maxMem, this.etiqueta + aux);
        }
    }
}