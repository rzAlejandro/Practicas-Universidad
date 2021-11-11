import java.util.concurrent.Semaphore;

public class Buffer implements Almacen{
    private Semaphore empty;
    private Semaphore full;
    private Semaphore mutexP;
    private Semaphore mutexC;
    private Producto buffer[];
    private int cantidad;
    private volatile int ini;
    private volatile int fin;

    
    public Buffer(int cantidad){
        empty=new Semaphore(cantidad);
        full=new Semaphore(0);
        mutexP=new Semaphore(1);
        mutexC=new Semaphore(1);
        buffer=new Producto[cantidad];
        for (int i=0;i<cantidad;++i){
            buffer[i]=new Producto();
        }
        this.cantidad=cantidad;
        ini=0;
        fin=0;

    }
    public void almacenar(Producto producto){
        try{
            empty.acquire();
        }catch(InterruptedException e){}
        try{
            mutexP.acquire();
        }catch(InterruptedException e){}

        buffer[fin].setValor(producto.getValor());
        System.out.println("Se almacena el producto con valor " + buffer[fin].getValor());
        fin=(fin+1)%cantidad;

        mutexP.release();
        full.release();
    }
    public Producto extraer(){
        try{
            full.acquire();
        }catch(InterruptedException e){}
        try{
            mutexC.acquire();
        }catch(InterruptedException e){}

        Producto obj=new Producto();
        obj.setValor(buffer[ini].getValor());
        System.out.println("Se consume el producto con valor " + obj.getValor());
        ini=(ini+1)%cantidad;
        

        mutexC.release();
        empty.release();
        return obj;
    }
    private int espacio(){
       return cantidad - vabs((fin + 1) - ini);
    }
    private int vabs(int x){
        if (x<0)
            return -x;
        else return x;
    }
}