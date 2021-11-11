public class Variable{
    private int x;
    public Variable(int x){
        this.x=x;
    }
    public void incrementarX(){
        x++;
    }
    public void decrementarX(){
        x--;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x=x;
    }
}