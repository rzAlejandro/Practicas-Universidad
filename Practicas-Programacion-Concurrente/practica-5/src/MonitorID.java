public class MonitorID {
    private int x;
    
    public MonitorID(){
        this.x=0;
    }

    public synchronized void inc_dec(boolean inc){
        if(inc)
            x++;
        else x--;
    }
    public int getX(){
        return x;
    }
    
}
