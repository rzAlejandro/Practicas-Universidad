public class Productores extends Thread{
    private int id;
    private Buffer buffer;
    private  int rep;
    private Producto p;
    
    public Productores(int id,Buffer buffer,Producto p,int rep){
        this.id=id;
        this.buffer=buffer;
        this.rep=rep;
        this.p=p;
    }
    public void run(){
        for(int i=0;i<rep;++i){
            p.producir();
            buffer.almacenar(p);
        }
    }
}