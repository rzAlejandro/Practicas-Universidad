import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main1 {

    static final int MAX_HILOS=10;
    static final int MAX_ID=200;
    static final int MAX_TIME=10000;
    public static void main (String[] args) throws InterruptedException {
        Random r=new Random();

		//creo la lista de hilos
		List <Thread> hilos = new ArrayList<>();
		//creamos los hilos y los lanzamos, le meto la funcion que ejecuta el hilo
		for(int i = 0; i < MAX_HILOS; ++i) {
			MyThread1 th = new MyThread1(r.nextInt(MAX_ID),r.nextInt((MAX_TIME))+1);
			hilos.add(th);
			hilos.get(i).start();
			
		}
		//espero a todos los hilos
		for(int i = 0; i < MAX_HILOS; ++i) {
			hilos.get(i).join();
			
		}
		
		System.out.println("Han terminado todos los hilos");
	}
}
