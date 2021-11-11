import java.io.Serializable;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class LockRompeEmpates implements Serializable{
    private AtomicIntegerArray ini;
    private AtomicIntegerArray last;
    private int N;

    public LockRompeEmpates(int t){
        ini=new AtomicIntegerArray(t);
        last=new AtomicIntegerArray(t);
        this.N=t;
    }

    public void takeLock(int id){
        int i=id-1;
        for(int j=0;j<N;++j){
            ini.set(i,j+1);
            last.set(j,id);
            //while(last.get(j)==id);
            for(int k=0;k<N;++k){
                if(k!=i){
                    while(ini.get(k)>=ini.get(i) && last.get(j)==id);
                }
            }
        }
    }
    public void releaseLock(int i){
        ini.set(i-1,0);
    }
}
