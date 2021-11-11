import java.util.ArrayList;
import java.util.Random;

public class Consumidores extends Thread{
    private int id;
    private MonitorLC monitor;
    private  int rep;
    private int max ;
    private Random rnd; 

    public Consumidores(int id,MonitorLC monitor,int rep,int max){
        this.id=id;
        this.monitor= monitor;
        this.rep=rep;
        this.max=max;
        rnd=new Random();
    }
    public void run(){
        int m=rnd.nextInt(max) + 1;
        ArrayList<Producto> sec=new ArrayList<Producto>();
        for(int i=0;i<rep;++i){
            try {
                sec=monitor.extraer(m,id);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}