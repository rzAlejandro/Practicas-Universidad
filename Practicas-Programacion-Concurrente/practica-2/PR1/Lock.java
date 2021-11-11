public class Lock {
    volatile boolean in1;
    volatile boolean in2;
    volatile int last;

    public Lock(boolean in1,boolean in2,int last){
        this.in1=in1;
        this.in2=in2;
        this.last=last;
    }

    public void setIn1(boolean valor){
        this.in1=valor;
    }
    public void setIn2(boolean valor){
        this.in2=valor;
    }
    public boolean  getIn1(){
        return in1;
    }
    public boolean getIn2(){
        return in2;
    }

    public void setLast(int last){
        this.last=last;
    }
    public int getLast(){
        return last;
    }
}
