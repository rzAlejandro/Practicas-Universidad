

/* EJERCICIO DE ANA Y ANGELES

import java.util.ArrayList;
import java.util.List;

public class Practica1{
	//num de hilos que creo
	private static final int N = 10;
	//tiempo de espera 
    private static final int T = 20;
	
	
	static class MiThread extends Thread {
        //lo que hace la tarea del hilo cuando lo creo
		public void run() {
			//hilo actual
			Thread miHilo = Thread.currentThread();
			//mostrar id del hilo
			System.out.println("Abrimos el hilo "+ miHilo.getId());
			
			
			//el hilo duerme un rato
			try {
				Thread.sleep(Practica1.T);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//mostrar id del hilo
			System.out.println("Cerramos el hilo " + miHilo.getId());

		}
	}

	
	public static void main (String[] args) throws InterruptedException {
		//creo la lista de hilos
		List <Thread> hilos = new ArrayList<>();
		//creamos los hilos y los lanzamos, le meto la funcion que ejecuta el hilo
		for(int i = 0; i < Practica1.N; ++i) {
			MiThread th = new MiThread();
			hilos.add(th);
			hilos.get(i).start();
			
		}
		//espero a todos los hilos
		for(int i = 0; i < Practica1.N; ++i) {
			hilos.get(i).join();
			
		}
		
		System.out.println("Han terminado todos los hilos");
		
		
	}

	
}

*/

public class Practica1{

}
