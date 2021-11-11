import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static Monitor monitor;
    private static TablaUsuarios tablaU;
    private static TablaInfo tablaInfo;
    private static int nUsers = 0; //Necesario para el lock del Puerto.
    private static int puertoInit = 500;
    private static String direccionIP="192.168.1.99";

    public static void main(String[] args) throws Exception {

        cargarFichero(args[0]);//la informacion de los usuarios registrados le llega al main
        System.out.println("Se cargaron correctamente los datos de los usuarios registrados\n");
        Puerto puertos = new Puerto(800); // Para los puertos de emitir ficheros (Cliente - Cliente)
        monitor = new Monitor();
        while (true){
            
            ServerSocket ss = new ServerSocket(puertoInit);
            Socket si = ss.accept(); 
            puertoInit++; 
            nUsers++;
            OyenteCliente OC = new OyenteCliente(si, monitor, tablaU, tablaInfo, puertos, nUsers);
            OC.start();
       }
    }

    public static void cargarFichero(String fichero){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        HashMap <String, ArrayList<String>> tabla_info = new HashMap<>();
        HashMap <String, Boolean> tabla_conectados = new HashMap<>();
        try {
            archivo = new File (fichero);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero. En cada línea tenemos el nombre del usuario y los ficheros que posee tal que así:
            // usuario fichero1 fichero2.
            String linea;
            while((linea = br.readLine())!=null){
                String [] infoUs = linea.split(" ");
                //Pasar a ArrayList
                ArrayList<String> ficheros = new ArrayList<String>(Arrays.asList(infoUs));
                ficheros.remove(0); //El primero es el usuario
                tabla_info.put(infoUs[0], ficheros);
                tabla_conectados.put(infoUs[0],false);
            }
            tablaU = new TablaUsuarios();
            tablaInfo = new TablaInfo(tabla_info,tabla_conectados);
               
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{                    
                if( null != fr ){   
                fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
    }

}
