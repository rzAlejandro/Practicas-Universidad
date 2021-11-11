import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class OyenteServidor extends Thread{
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private ObjectInputStream fin;
    private ObjectOutputStream fout;
    private Semaphore arriveOS; //Funciona a modo de BARRERA con la interfaz del usuario
    private String user; //Para saber que carpeta le corresponde a la hora de descargar (se utiliza en Emisor)

    public OyenteServidor(ObjectInputStream fin,ObjectOutputStream fout, Semaphore arriveOS, String user) throws IOException{
        this.fin = fin;
        this.fout = fout; //Necesita enviar que esta preparado para emitir fichero cuando se lo pidan.
        this.arriveOS = arriveOS;
        this.user = user;
    }

    public void run(){
        try {
            boolean stop = false;
            while(!stop){
                Object ms=new Mensaje();
                ms = (Object) fin.readObject();
                Mensaje m=(Mensaje)ms;
                switch(m.getTipo()){

                    case 1: //Se ha establecido conexión correctamente
                        Cliente.setConected(true);
                        Cliente.setMensaje("\nSe ha conectado a la base de datos");
                        arriveOS.release();
                        break;

                    case 3: // llega mensaje con la lista de usuarios y sus datos
                        Cliente.setMensaje(crearLista(((M_Conf_LU)m).getInfo(), ((M_Conf_LU)m).getConects()));
                    	arriveOS.release();
                    	break;
                    
                    case 5: // llega mensaje de emitir_fichero, por lo que le exigen su IP 
                        int puerto = ((M_Emit_Fich) m).getPuerto();
                        M_Prep_CS mPc = new M_Prep_CS("localhost", puerto ,((M_Emit_Fich) m).getReceptor());
                        Emisor emisor = new Emisor(user, puerto, ((M_Emit_Fich)m).getFich());
                        emisor.start();
                        fout.writeObject(mPc);
                        fout.flush();
                        break;

                    case 7: //El emisor del fichero ya está preparado
                        Receptor receptor = new Receptor(user, ((M_Prep_SC)m).getdIP(), ((M_Prep_SC)m).getPuerto());
                        receptor.start();
                        Cliente.setMensaje("\nEl fichero se descarga correctamente...\n");
                        arriveOS.release();
                        break;

                    case 9: //Le llega el mensaje de confirmacion de cierre de conexion
                        Cliente.setMensaje("\nHasta la proxima!");
                        arriveOS.release();
                        stop = true;
                        break;

                    case 10: //Error en la conexión
                        Cliente.setConected(false);
                        Cliente.setMensaje(((M_Error_Conexion)m).getError());
                        arriveOS.release();
                        stop = true;
                        break;
                    
                    case 11: //Error al pedir el fichero
                        Cliente.setMensaje(((M_Error_Fichero)m).getError());
                        arriveOS.release();
                        break;
                    
                    case 12: //Error al emitir fichero por falta de receptor
                        //Avisar a emisor de que no debe seguir con su ejecucion
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	private String crearLista(HashMap<String, ArrayList<String>> info, HashMap<String, Boolean> conects) {
        String r = "";
        int cont = 1;
		for(String l: info.keySet()){
            if(conects.get(l)){
                r += cont + ". " + l + ": " + info.get(l).toString() + " CONECTADO" + "\n"; 
            }
            else{
                r += cont + ". " + l + ": " + info.get(l).toString() + " DESCONECTADO" + "\n"; 
            }
           
			cont++;
		}
        return r;
	}

    private String crearListaMAC(HashMap<String, ArrayList<String>> info, HashMap<String, Boolean> conects) {
        String r = "";
        int cont = 1;
		for(String l: info.keySet()){
            // Mirar para pintar con diferentes colores si conectado o no. SOLO FUNCIONA CON MAC
            if(conects.get(l)){
               r += ANSI_GREEN + cont + ". " + l + ANSI_RESET + ": " + info.get(l).toString(); 
            }
            else{
                r += ANSI_RED + cont + ". " + l + ANSI_RESET + ": " + info.get(l).toString(); 
            }
           
			cont++;
		}
        return r;
	}
}
