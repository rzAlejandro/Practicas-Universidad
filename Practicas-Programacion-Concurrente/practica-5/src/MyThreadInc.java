public class MyThreadInc extends Thread{
    private MonitorID monitor;
    private int id;
    static final int N=100;

    
    public MyThreadInc(int id,MonitorID monitor){
        this.id=id;
        this.monitor=monitor;
    }
    public void run(){
        for(int i=0;i<N;++i){
            monitor.inc_dec(true);
        }
    }
}