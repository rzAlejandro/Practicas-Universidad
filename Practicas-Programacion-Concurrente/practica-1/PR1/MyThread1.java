public class MyThread1 extends Thread{
	private int id;
	private int t;

	public MyThread1(int id,int t){
		this.id=id;
		this.t=t;
	}
	
	public void run(){
	
		System.out.println("Abriendo el hilo " + this.id+"\n");
		try {
			Thread.sleep(this.t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//mostrar id del hilo
		System.out.println("Cerramos el hilo " + this.id+"\n");
	}
}