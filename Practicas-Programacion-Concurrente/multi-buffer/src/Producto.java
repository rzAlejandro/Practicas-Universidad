import java.util.Random;
public class Producto{
    private int valor;
    private Random rnd;

    public Producto(){
        rnd=new Random();
        valor=rnd.nextInt(100);
    }
    public int getValor(){
        return valor;
    }
    public void setValor(int v){
        valor=v;
    }
}