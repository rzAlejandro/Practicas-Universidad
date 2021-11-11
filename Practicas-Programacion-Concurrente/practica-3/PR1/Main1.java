import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main1 {
    static final int M=10;
 
    public static void main (String[] args) throws InterruptedException {
        Variable x=new Variable(0);
        Semaphore mutex=new Semaphore(1);
        int n=2*M;
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<M){
                hilos.add(new MyThreadDec(i+1,x,mutex));
            }
            else{
                hilos.add(new MyThreadInc(i+1,x,mutex));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
        System.out.println("El valor de x es: " + x.getX()+ "\n");
    }
}