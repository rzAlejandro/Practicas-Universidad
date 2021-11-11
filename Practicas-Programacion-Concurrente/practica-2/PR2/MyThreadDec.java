public class MyThreadDec extends Thread{
    private Variable x;
    private Lock l;
    private int id;
    static final int N=100;

    public MyThreadDec(int id,Variable x,Lock l){
        this.x=x;
        this.l=l;
        this.id=id;
    }
    public void run(){
        for(int i=0;i<N;++i){
            l.takeLock(id);
            x.decrementarX();
            l.releaseLock(id);
        }
    }
}
