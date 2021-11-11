import java.util.ArrayList;
import java.util.Random;

public class Productores extends Thread{

    private int id;
    private MonitorLC monitor;
    private int rep;
    private int max;
    private Random rnd;

    public Productores(int id,MonitorLC monitor,int rep,int max){
        this.id=id;
        this.monitor=monitor;
        this.rep=rep;
        this.max=max;
        rnd=new Random();
    }
   
    public void run(){
        for(int i=0;i<rep;++i){
            int m=rnd.nextInt(max) + 1;
            ArrayList<Producto> sec=new ArrayList<Producto>();
            for(int j=0;j<m;++j){
                sec.add(new Producto());
            }
            try {
                monitor.almacenar(sec,id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}