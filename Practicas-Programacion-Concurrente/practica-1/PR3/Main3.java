import java.util.ArrayList;
import java.util.List;

public class Main3 {
    static final int N=10;
    public static void main (String[] args) throws InterruptedException {
        Matriz A=new Matriz(N,false);
        Matriz B= new Matriz(N,false);
        Matriz C=new Matriz(N,true);
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<N;++i){
            //ArrayList<Integer> fila=new ArrayList<Integer>();
            //fila=A.getRow(i);
            hilos.add(new MyThread3(i,A.getRow(i),B,C));
            hilos.get(i).start();
        }
        for(int i = 0; i < N; ++i) {
            hilos.get(i).join();   
        }
        C.print();
    }
}