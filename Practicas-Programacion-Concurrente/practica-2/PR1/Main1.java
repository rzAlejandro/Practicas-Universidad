import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main (String[] args) throws InterruptedException {
        Variable x=new Variable(0);
        Lock lock=new Lock(false,false,0);
        MyThreadDec td=new MyThreadDec(x,lock);
        MyThreadInc ti= new MyThreadInc(x,lock);
        td.start();
        ti.start();
        td.join();
        ti.join();
       
        System.out.println("El valor de x es: " + x.getX()+ "\n");
    }
}
