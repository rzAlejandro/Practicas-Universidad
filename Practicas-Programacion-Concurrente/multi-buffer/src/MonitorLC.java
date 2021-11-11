import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorLC{
    private int ini;
    private int fin;
    private int cont;
    private int capacidad;
    private int productores;//numero de productores que han finalizado su ejecucion
    private int consumidores;//numero de consumidores que han finalizado su ejecucion
    private int nProd;//Productores que se esperan.
    private int nCons;//Consumidores que se esperan.
    private Producto buffer[];
    private final ReentrantLock l=new ReentrantLock(true);
    private final Condition empty=l.newCondition();
    private final Condition full=l.newCondition();

    public MonitorLC(int c,int nProd,int nCons){
        ini=0;
        fin=0;
        cont=0;
        capacidad=c;
        productores=0;
        consumidores=0;
        this.nProd=nProd;
        this.nCons=nCons;
        buffer=new Producto[capacidad];
        for (int i=0;i<capacidad;++i){
            buffer[i]=new Producto();
        }
    }

    public void almacenar(ArrayList<Producto> sec,int id)throws InterruptedException{
        l.lock();
        int n=sec.size();
        if(consumidores==nCons && n>(capacidad-cont)){//Almacenar todos los que sean posibles aunque no haya mas consumidores a continuacion
            n=capacidad-cont;
        }
        for(int i=0;i<n;++i){
            while(cont==capacidad && consumidores<nCons){
                empty.await();
                if(consumidores==nCons){//Si le despierta el ultimo consumidor, no producir más de la cuenta.
                    if(cont==capacidad){//En este caso ya no puede producir más
                        throw new InterruptedException();
                    }
                    if(n-i>capacidad-cont)
                        n=i+capacidad-cont;
                }
            } 
            buffer[fin].setValor(sec.get(i).getValor());
            cont++;
            System.out.println("El hilo " + id + " almacena el producto con valor " + buffer[fin].getValor()+ " . Le quedan por almacenar "+ (sec.size()-i-1));
            fin=(fin+1)%capacidad;
            full.signalAll();
        }
        productores++; 
        System.out.println("Quedan " + (nProd - productores) + " productores por ejecutar");
        l.unlock();
    }

    public ArrayList<Producto> extraer(int n,int id) throws InterruptedException{
        l.lock();
        ArrayList<Producto> sec=new ArrayList<Producto>();
        if(productores==nProd && n>cont){ //Extraer todos los maximos posibles aunque no queden productores.
            n=cont;
        }
        for(int i=0;i<n;++i){
            while(cont==0 && productores<nProd){
                full.await();
                if(productores==nProd){ //Si le despierta el ultimo, consumir tantos productos como sea posible.
                    if(cont==0){// Ya no debe consumir nada más.
                        l.unlock();
                        return sec;
                    }
                    if(n-i>cont)
                        n=i+cont;
                }
            }

            Producto obj=new Producto();
            obj.setValor(buffer[ini].getValor());
            sec.add(obj);
            System.out.println("El hilo "+id+" consume el producto con valor " + obj.getValor()+ " . Le quedan por consumir "+ (n-i-1));
            ini=(ini+1)%capacidad;
            cont--;
            empty.signalAll();
        }
        consumidores++;
        System.out.println("Quedan " + (nCons - consumidores) + " consumidores por ejecutar");
        l.unlock();
        return sec;
    }
}
