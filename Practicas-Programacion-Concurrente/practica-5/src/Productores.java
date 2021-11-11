public class Productores extends Thread{
    private int id;
    private MonitorPC monitor;
    private Producto p;
    private int rep;
   
    
    public Productores(int id,MonitorPC monitor,Producto p,int rep){
        this.id=id;
        this.monitor=monitor;
        this.rep=rep;
        this.p=p;
        
    }
    public void run(){
        for(int i=0;i<rep;++i){
            p.producir();
            try {
                monitor.almacenar(p);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}