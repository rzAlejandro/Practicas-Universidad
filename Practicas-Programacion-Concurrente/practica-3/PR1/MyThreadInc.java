import java.util.concurrent.Semaphore;

public class MyThreadInc extends Thread{
    private Variable x;
    static final int  N=100;
    private int id;
    private Semaphore mutex;
    
    public MyThreadInc(int id, Variable x,Semaphore mutex){
        this.x=x;
        this.id=id;
        this.mutex=mutex;
    }
    public void run(){
        for(int i=0;i<N;++i){
            try{
                mutex.acquire();
            }catch(InterruptedException e){}
           
            x.incrementarX();
            mutex.release();
        }
    }
}