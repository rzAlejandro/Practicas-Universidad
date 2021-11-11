import java.util.ArrayList;


public class Monitor {
    private int ini;
    private int fin;
    private int cont;
    private int capacidad;
    private Producto buffer[];

    public Monitor(int c){
        ini=0;
        fin=0;
        cont=0;
        capacidad=c;
        buffer=new Producto[capacidad];
        for (int i=0;i<capacidad;++i){
            buffer[i]=new Producto();
        }
    }

    public synchronized void almacenar(ArrayList<Producto> sec,int id)throws InterruptedException{
        for(int i=0;i<sec.size();++i){
            while(cont==capacidad) wait();
            buffer[fin].setValor(sec.get(i).getValor());
            cont++;
            System.out.println("El hilo " + id + " almacena el producto con valor " + buffer[fin].getValor()+ " . Le quedan por almacenar "+ (sec.size()-i-1));
            fin=(fin+1)%capacidad;
            notifyAll();
        }
        
    }

    public synchronized ArrayList<Producto> extraer(int n,int id) throws InterruptedException{
        ArrayList<Producto> sec=new ArrayList<Producto>();
        for(int i=0;i<n;++i){
            while(cont==0) wait();
            Producto obj=new Producto();
            obj.setValor(buffer[ini].getValor());
            sec.add(obj);
            System.out.println("El hilo "+id+" consume el producto con valor " + obj.getValor()+ " . Le quedan por consumir "+ (n-i-1));
            ini=(ini+1)%capacidad;
            cont--;
            notifyAll();
        }
        
        return sec;
    }
}
