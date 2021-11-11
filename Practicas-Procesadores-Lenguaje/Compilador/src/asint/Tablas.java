package asint;

import java.util.*;
import ast.Nodo;
import ast.*;

public class Tablas {
    public List<HashMap<String,Nodo>> pilaTablas;
    public ArrayList<Funcion> nodoFuncs; //para chequear el tipo del return

    public Tablas(){
        pilaTablas = new ArrayList<HashMap<String,Nodo>>();
        nodoFuncs = new ArrayList<Funcion>();
    }


    public void abreBloque(){
        pilaTablas.add(new HashMap<String,Nodo>());
    }


    public void cierraBloque(){
        if(pilaTablas.size() != 0)
            pilaTablas.remove(pilaTablas.size() - 1);
    }


    public void insertaId(String id, Nodo puntero){ //No permitimos que haya variables con nombres de enums, structs o funciones.
        if(pilaTablas.size() == 1 && (puntero instanceof Funcion)){//Permitimos funciones con mismo nombre, pero diferente numero de parametros.
            boolean insertar = true;
            ArrayList<Funcion> funcs = getFuncs();

            for(int i = 0; i < funcs.size() && insertar; ++i){
                Funcion f = funcs.get(i);
                if(f.id.equals(id) && (f.parametros.size() == ((Funcion)puntero).parametros.size())){//Comprobar que tienen mismo nº parametros y nombre
                    insertar = false;
                }
            }

            if(insertar){
                pilaTablas.get(0).put(id,puntero);
            }
        }
        else{
            if (!pilaTablas.get(pilaTablas.size() - 1).containsKey(id) || !pilaTablas.get(pilaTablas.size() - 1).containsKey(id))
                pilaTablas.get(pilaTablas.size() - 1).put(id, puntero);
        }
    }


    public Nodo buscaId(String id){
        boolean stop = false;
        Nodo nodo = null;

        for(int i = pilaTablas.size() - 1; i >= 0  && !stop; --i){
            if(pilaTablas.get(i).containsKey(id)){
                stop = true;
                nodo = pilaTablas.get(i).get(id);
            }
        }

        return nodo;
    }

    //Devuelve todos los objetos enums definidos para la vinculación.
    public ArrayList<EnumClass> getEnums(){
        ArrayList<EnumClass> enums = new ArrayList<EnumClass>();
        HashMap<String, Nodo> tabla = pilaTablas.get(0);
        Set<String> set = tabla.keySet();

        for(String s : set){
            if(tabla.get(s) instanceof EnumClass){
                enums.add((EnumClass)tabla.get(s));
            }
        }

        return enums;
    }

    //Devuelve todos los objetos funciones definidos para añadir funciones de mismo nombre y diferente nº parametros.
    public ArrayList<Funcion> getFuncs(){
        ArrayList<Funcion> funcs = new ArrayList<Funcion>();
        HashMap<String, Nodo> tabla = pilaTablas.get(0);
        Set<String> set = tabla.keySet();

        for(String s : set){
            if(tabla.get(s) instanceof Funcion){
                funcs.add((Funcion)tabla.get(s));
            }
        }

        return funcs;
    }

    public ArrayList<Struct> getStructs(){ //Para comprobar tipo en accesos a variables de structs
        ArrayList<Struct> structs = new ArrayList<Struct>();
        HashMap<String, Nodo> tabla = pilaTablas.get(0);
        Set<String> set = tabla.keySet();

        for(String s : set){
            if(tabla.get(s) instanceof Struct){
                structs.add((Struct)tabla.get(s));
            }
        }
        return structs;
    }
}