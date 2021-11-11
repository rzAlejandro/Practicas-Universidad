package ast;


public class MainFuncion extends Nodo{
    public Bloque bloque;
    public static int maxMem;

    public MainFuncion(Bloque bloque){
        this.bloque = bloque;
    }

    public String toString(){
        return ("Main {\n" + bloque.toString() + "\n}");
    }

    public void vincula(){
        bloque.vincula();
    }

    public void chequea(){
        bloque.chequea();
    }

    public String generaCodigo() {
        String str = "";
        str += "(local $localsStart i32)\n";
        str += "i32.const " + maxMem*4  + ";; let this be the stack size needed (params+locals+2)*4\n";
        str += "call $reserveStack  ;; returns old MP (dynamic link)\n";
        str += "get_global $MP\n";
        str += "i32.store\n";
        str += "get_global $MP\n";
        str += "get_global $SP\n";
        str += "i32.store offset=4\n";
        str += "get_global $MP\n";
        str += "i32.const 8\n";
        str += "i32.add\n";
        str += "set_local $localsStart\n";
        str += bloque.generaCodigo();
        str += "call $freeStack\n";
        return str;
    }

    public void delta() {
        bloque.delta();        
    }

    public void memoria() {
        bloque.memoria();
    }
}