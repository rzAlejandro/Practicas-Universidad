public class MyThreadInc extends Thread{
    private int id;
    private Variable x;
    static final int  N=5000;
    private Lock l;

    public MyThreadInc(int id,Variable x,Lock l){
        this.x=x;
        this.id=id;
        this.l=l;
    }
    public void run(){
        for(int i=0;i<N;++i){
            l.takeLock(id);
            x.incrementarX();
            l.releaseLock(id);
        }
    }
}