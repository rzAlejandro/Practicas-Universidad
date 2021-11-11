import java.util.ArrayList;
import java.util.List;

public class App {
    static final int nProductores=3;
    static final int nConsumidores=3;
    static final int repProd = 1; 
    static final int capacidad=5; 
    public static void main(String[] args) throws Exception {
        //monitor();
        lock_conditions();
       
    } 
    /*
    public static void monitor() throws InterruptedException{
        int n=nProductores+nConsumidores;
        int repConsumidor=repProd*nProductores/nConsumidores;
        Monitor monitor=new Monitor(capacidad);
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<nProductores){
                hilos.add(new Productores(i+1,monitor,repProd,capacidad));
            }
            else{
                hilos.add(new Consumidores(i+1,monitor,repConsumidor,capacidad));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
    }
    */
    public static void lock_conditions() throws InterruptedException{
        int n=nProductores+nConsumidores;
        int repConsumidor=repProd*nProductores/nConsumidores;
        MonitorLC monitor=new MonitorLC(capacidad,nProductores*repProd,nConsumidores*repConsumidor);
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<nProductores){
                hilos.add(new Productores(i+1,monitor,repProd,capacidad));
            }
            else{
                hilos.add(new Consumidores(i+1,monitor,repConsumidor,capacidad));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
    }
}
