public class MyThreadInc extends Thread{
    private Variable x;
    static final int  N=100;
    private Lock l;
    private int id;

    
    public MyThreadInc(int id, Variable x,Lock l){
        this.x=x;
        this.l=l;
        this.id=id;
    }
    public void run(){
        for(int i=0;i<N;++i){
            l.takeLock(id);
            x.incrementarX();
            l.releaseLock(id);
        }
    }
}