import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matriz {

    private ArrayList<ArrayList<Integer>> matriz;
    private int size;
    static final int MAX_NUM=10;

    public Matriz(int N,boolean cero){
        //Genera matriz random de N*N
        size=N;
        matriz=new ArrayList<>();
        Random r=new Random();
        for(int i=0;i<N;++i){
            ArrayList<Integer> row=new ArrayList<>();
            for(int j=0;j<N;++j){
                if(!cero)
                    row.add(r.nextInt(MAX_NUM));
                else row.add(0);
            }
            matriz.add(row);
        }
    }
    
    public ArrayList<Integer> getRow(int i){
        //Falta controlar con una excepcion que la fila exista.
        return matriz.get(i);
    }

    public ArrayList<Integer> getColumn(int j){
        ArrayList<Integer> c=new ArrayList();
        for(int i=0;i<size;++i){
            c.add(matriz.get(i).get(j));
        }
        return c;
    }
    public void setRow(int i,ArrayList<Integer> newRow){
        matriz.set(i,newRow);
    }

    public void print(){
        for(int i=0;i<size;++i){
            int j=0;
            ArrayList<Integer> fila=new ArrayList<Integer>();
            fila=this.getRow(i);
            while(j<size-1){
                System.out.print(fila.get(j)+ " ");
                ++j;
            }
            System.out.println(fila.get(j));
        }
    }
}
