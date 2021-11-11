public class MyThreadDec extends Thread{
    private Variable x;
    private int n;
    public MyThreadDec(Variable x,int n){
        this.x=x;
        this.n=n;
    }
    public void run(){
        for(int i=0;i<n;++i){
            x.decrementarX();
        }
    }
}
