import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    // Utilizamos monitor para controlar lectura y escritura sobre tabla_info.
    private int nr;
    private int nw;
    private final ReentrantLock l = new ReentrantLock(true);
    private final Condition okWrite = l.newCondition();
    private final Condition okRead = l.newCondition();
    
    public Monitor() {
        nr = 0;
        nw = 0;
    }

    public void request_read() throws InterruptedException{
        l.lock();
        while(nw > 0){
            okRead.await();
        }
        nr++;
        l.unlock();
    }

    public void release_read(){
        l.lock();
        nr--;
        if(nr == 0){
            okWrite.signal();
        }
        l.unlock();
    }

    public void request_write() throws InterruptedException{
        l.lock();
        while(nr > 0 || nw > 0){
            okWrite.await();
        }
        nw++;
        l.unlock();
    }

    public void release_write(){
        l.lock();
        nw--;
        okWrite.signal();
        okRead.signalAll();
        l.unlock();
    }
     
}
