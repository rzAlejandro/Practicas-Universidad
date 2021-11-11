public class Consumidores extends Thread{
    private int id;
    private Buffer buffer;
    private  int rep;

    public Consumidores(int id,Buffer buffer,int rep){
        this.id=id;
        this.buffer= buffer;
        this.rep=rep;
    }
    public void run(){
        Producto p=new Producto();
        for(int i=0;i<rep;++i){
            p=buffer.extraer();
            p.consumir();
        }
    }
}