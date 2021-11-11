import java.util.concurrent.Semaphore;


public class MyThreadDec extends Thread{
    private Variable x;
    private Semaphore mutex;
    private int id;
    static final int N=100;

    public MyThreadDec(int id,Variable x,Semaphore mutex){
        this.x=x;
        this.id=id;
        this.mutex=mutex;
    }
    public void run(){
        for(int i=0;i<N;++i){
            try{
                mutex.acquire();
            }catch(InterruptedException e){}
            x.decrementarX();
            mutex.release();
        }
    }
}