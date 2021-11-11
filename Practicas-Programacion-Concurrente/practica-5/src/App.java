import java.util.ArrayList;
import java.util.List;

public class App {
    static final int M=10;
    static final int nProductores=2;
    static final int nConsumidores=2;
    static final int repProd = 10; 
    static final int capacidad=5; 
    public static void main(String[] args) throws Exception {
       //incrementar_decrementar();
        productores_consumidores();
    }

    public static void incrementar_decrementar() throws InterruptedException{
        int n=2*M;
        MonitorID monitor=new MonitorID();
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<M){
                hilos.add(new MyThreadDec(i+1,monitor));
            }
            else{
                hilos.add(new MyThreadInc(i+1,monitor));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
        System.out.println("El valor de x es: " + monitor.getX()+ "\n");
    }



    public static void productores_consumidores() throws InterruptedException{
        int n=nProductores+nConsumidores;
        int repConsumidor=repProd*nProductores/nConsumidores;
        MonitorPC monitor=new MonitorPC(capacidad);
        Producto p=new Producto();
        List <Thread> hilos=new ArrayList<>();
        for(int i=0;i<n;++i){
            if(i<nProductores){
                hilos.add(new Productores(i+1,monitor,p,repProd));
            }
            else{
                hilos.add(new Consumidores(i+1-nProductores,monitor,repConsumidor));
            }
            hilos.get(i).start();
        }
        for(int i = 0; i < n; ++i) {
            hilos.get(i).join();    
        }
    }  
}
