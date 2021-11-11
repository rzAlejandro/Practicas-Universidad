import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class TablaUsuarios {

    private int nr;
    private int nw;
    private int dr;
    private int dw;
    private Semaphore read;
    private Semaphore write;
    private Semaphore mutex;
    private Map <String,ParCanales> tabla_usuarios; //usuario con canal de entrada y salida
    
    public TablaUsuarios(){
        this.tabla_usuarios = new HashMap<String,ParCanales>();
        read = new Semaphore(0);
        write = new Semaphore(0);
        mutex = new Semaphore(1);
        nr = 0;
        dr = 0;
        nw = 0;
        dw = 0;
    }

    public void request_read() throws InterruptedException{
        mutex.acquire();
        if(nw > 0){
           dr++;
           //lock.releaseLock(id);
           mutex.release();
           read.acquire();
        }
        nr++;
        mutex.release();
    }

    public void release_read() throws InterruptedException{
        mutex.acquire();
        nr--;
        if(nr == 0 && dw > 0){
            dw--;
            write.release();
        }
        else{
            if(dr > 0){
                dr--;
                read.release();
            }
            else  mutex.release(); 
        }
    }
    public void request_write() throws InterruptedException{
        mutex.acquire();
        if(nr > 0 || nw > 0){
            dw++;
            //lock.releaseLock(id);
            mutex.release();
            write.acquire();
        }
        nw++;
        mutex.release();
    }

    public void release_write() throws InterruptedException{
        mutex.acquire();
        nw--;
        if(dw > 0){
            dw--;
            write.release();
        }
        else{
            if(dr > 0){
                dr--;
                read.release();
            }
            else{
                mutex.release();
            }
        }
    }

    public void addUser(String id_user, ParCanales parCanales) {
        tabla_usuarios.put(id_user,parCanales);
    }

	public void deleteUser(String id) {
		tabla_usuarios.remove(id);	
	}

    public ObjectOutputStream getOutStream(String user) {
        if(tabla_usuarios.containsKey(user))
            return tabla_usuarios.get(user).second();
        else return null;
    }
}
