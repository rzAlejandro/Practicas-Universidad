import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main2 {
    static final int nProductores=2;
    static final int nConsumidores=2;
    static final int repProd = 10; 
    static final int capacidad=5; 
    public static void main (String[] args) throws InterruptedException {
        //Semaphore mutex=new Semaphore(1);
        int n=nProductores+nConsumidores;
        int repConsumidor=repProd*nProductores/nConsumidores; //Requiere que nConsumidores|nProductores.
        Buffer buffer=new Buffer(capacidad);
        Producto p=new Producto();
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<nProductores){
                hilos.add(new Productores(i+1,buffer,p,repProd));
            }
            else{
                hilos.add(new Consumidores(i+1-nProductores,buffer,repConsumidor));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
    }
}