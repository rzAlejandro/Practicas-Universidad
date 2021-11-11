import java.util.ArrayList;
import java.util.List;

public class Main2 {
    static final int M=5;
    static final int N=10;
    public static void main (String[] args) throws InterruptedException {
        Variable x=new Variable(0);
        int cont=0;
        
        for(int rep=0;rep<50;++rep){
            List <Thread> hilos=new ArrayList<>();
            for(int i=0;i<N;++i){
                if(i<M){
                    hilos.add(new MyThreadDec(x,N));
                }
                else{
                    hilos.add(new MyThreadInc(x,N));
                }
                hilos.get(i).start();
            }
            for(int i = 0; i < N; ++i) {
                hilos.get(i).join();
                
            }
            if(x.getX()!=0)cont++;
            x.setX(0);
        }
        System.out.println("El nº de veces que ha fallado el código es: " + cont+ "\n");

    }
}
