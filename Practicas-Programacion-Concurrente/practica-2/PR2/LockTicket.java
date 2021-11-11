import java.util.concurrent.atomic.*;

public class LockTicket implements Lock{
    private volatile AtomicInteger number;
    private volatile int next;
    private int turno[];

    public LockTicket(int t){
        number=new AtomicInteger(1);
        next=1;
        turno=new int[t];
    }

    public void takeLock(int id){
        int i=id-1;
        turno[i]=number.getAndAdd(1);
        while(turno[i]!=next);
    }
    public void releaseLock(int i){
        next=next+1;
    }
}
