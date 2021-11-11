import java.util.concurrent.Semaphore;

public class MonitorPC {
    private int capacidad;
    private int ini;
    private int fin;
    private Producto buffer [];
    private int cont;

    public MonitorPC(int capacidad){
        
        this.capacidad=capacidad;
        this.ini=0;
        this.fin=0;
        this.cont=0;
        buffer=new Producto[capacidad];
        for (int i=0;i<capacidad;++i){
            buffer[i]=new Producto();
        }
    }

    public synchronized void almacenar(Producto p) throws InterruptedException{
        while(cont==capacidad) wait();
        buffer[fin].setValor(p.getValor());
        cont++;
        System.out.println("Se almacena el producto con valor " + buffer[fin].getValor());
        fin=(fin+1)%capacidad;
        notify();
    }

    public synchronized Producto extraer() throws InterruptedException{
        while(cont==0) wait();
        Producto obj=new Producto();
        obj.setValor(buffer[ini].getValor());
        System.out.println("Se consume el producto con valor " + obj.getValor());
        ini=(ini+1)%capacidad;
        cont--;
        notify();
        return obj;
    }
}
