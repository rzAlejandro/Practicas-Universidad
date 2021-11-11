package ast;

import asint.Tablas;

public class Programa extends Nodo{
    public StructEnum listaStructEnums;
    public Funciones listaFunciones;
    public MainFuncion main;
    public static Tablas tablas;
    public static int deltaCont;
    public static boolean okVincula;
    public static boolean okChequea;

    public Programa(StructEnum strenum, Funciones funcs, MainFuncion main){
        this.listaStructEnums = strenum;
        this.listaFunciones = funcs;
        this.main = main;
        tablas = new Tablas();
        deltaCont = 0;
        okVincula = true;
        okChequea = true;
    }

    public String toString(){
        return ("PROGRAMA : \n" + listaStructEnums.toString() + " \n\n" + listaFunciones.toString() + " \n\n" + main.toString());
    }

    public void vincula(){
        tablas.abreBloque(); //bloque visible para todo el programa (ahÃ­ van enums structs y funciones)
        listaStructEnums.vincula();
        listaFunciones.vincula();
        main.vincula();
        //tablas.cierraBloque(); no cerramos para hacer el getStructs y eso...
    }

    public void chequea(){
        listaStructEnums.chequea();
        listaFunciones.chequea();
        main.chequea();
    }

    public String generaCodigo(){
        String str = "";
        str += "(module\n";
        str += "(type $_sig_i32ri32 (func (param i32) (result i32)))\n";
        str += "(type $_sig_i32 (func (param i32)))\n";
        str += "(type $_sig_ri32 (func (result i32)))\n";
        str += "(type $_sig_void (func ))\n";
        str += "(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\n";
        str += "(import \"runtime\" \"print\" (func $print (param i32)))\n";
        str += "(import \"runtime\" \"read\" (func $read (result i32)))\n";
        str += "(memory 2000)\n";
        str += "(global $SP (mut i32) (i32.const 0)) ;; start of stack\n";
        str += "(global $MP (mut i32) (i32.const 0)) ;; mark pointer\n";
        str += "(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000641024-4\n";
        str += "(start $init)\n";
        str += "(func $init\n";
        str += listaStructEnums.generaCodigo() + listaFunciones.generaCodigo() + main.generaCodigo();
        str += ")\n";
        str += "(func $reserveStack (param $size i32)\n";
        str += "(result i32)\n";
        str += "get_global $MP\n";
        str += "get_global $SP\n";
        str += "set_global $MP\n";
        str += "get_global $SP\n";
        str += "get_local $size\n";
        str += "i32.add\n";
        str += "set_global $SP\n";
        str += "get_global $SP\n";
        str += "get_global $NP\n";
        str += "i32.gt_u\n";
        str += "if\n";
        str += "i32.const 3\n";
        str += "call $exception\n";
        str += "end\n";
        str += ")\n";
        str += "(func $freeStack (type $_sig_void)\n";
        str += "get_global $MP\n";
        str += "i32.load offset=4\n";
        str += "set_global $SP\n";
        str += "get_global $MP\n";
        str += "i32.load\n";
        str += "set_global $MP\n";
        str += ")\n";
        str += "(export \"init\" (func $init))\n";
        str += ")";
        return str;
    }

    public void delta() {
        listaStructEnums.delta();
        Programa.deltaCont = 0;
        listaFunciones.delta();
        Programa.deltaCont = 0;
        main.delta();       
    }

    public void memoria() {
        main.memoria();       
    }
}