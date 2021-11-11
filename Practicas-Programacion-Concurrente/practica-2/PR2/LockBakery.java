public class LockBakery implements Lock{
    private volatile int turno [];
    private int N;

    public LockBakery(int t){
       turno=new int[t];
       for(int i=0;i<t;++i){
           turno[i]=1;
           turno=turno;
       }
       this.N=t;
    }
    public void takeLock(int id){
        int i=id-1;
        turno[i]=max() + 1;
        turno=turno;
        for(int j=0;j<N;++j){
            if(j!=i){
                while(turno[j] !=0 && newOp(turno[i],i,turno[j],j));
            }
        }
    }
    public void releaseLock(int id){
        int i=id-1;
        turno[i]=0;
        turno=turno;
    }
    private int max(){
        int x=0;
        for(int i=0;i<N;++i){
            if(turno[i]>x)
                x=turno[i];
        }
        return x;
    }

    private boolean newOp(int ti,int i,int tj,int j){
        return (ti>tj || (ti==tj && i>j));
    }
}
