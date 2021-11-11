public class MyThreadInc extends Thread{
    private Variable x;
    private int n;
    public MyThreadInc(Variable x,int n){
        this.x=x;
        this.n=n;
    }
    public void run(){
        for(int i=0;i<n;++i){
            x.incrementarX();;
        }
    }
}
