import java.io.Serializable;

public class Mensaje implements Serializable{
    
    private int tipo;

    public Mensaje(){}

    public Mensaje(int t){
        tipo=t;
    }

    public int getTipo(){
        return tipo;
    }
	
}
