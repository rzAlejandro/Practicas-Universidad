import java.util.ArrayList;
import java.util.List;

public class Main2 {
    static final int M=10;

    public static void main (String[] args) throws InterruptedException {
        Variable x=new Variable(0);
        int n=2*M;
        Lock lockR=new LockRompeEmpates(n);
        Lock lockT=new LockTicket(n);
        Lock lockB=new LockBakery(n);
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<M){
                hilos.add(new MyThreadDec(i+1,x,lockR));
            }
            else{
                hilos.add(new MyThreadInc(i+1,x,lockR));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
        System.out.println("El valor de x es: " + x.getX()+ "\n");
    }
}