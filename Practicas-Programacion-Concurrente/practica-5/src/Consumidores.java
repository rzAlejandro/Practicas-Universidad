public class Consumidores extends Thread{
    private int id;
    private MonitorPC monitor;
    private  int rep;

    public Consumidores(int id,MonitorPC monitor,int rep){
        this.id=id;
        this.monitor= monitor;
        this.rep=rep;
    }
    public void run(){
        Producto p=new Producto();
        for(int i=0;i<rep;++i){
            try {
                p=monitor.extraer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            p.consumir();
        }
    }
}