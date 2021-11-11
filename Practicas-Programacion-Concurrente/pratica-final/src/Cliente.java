import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Cliente {
    private static String mensaje;
    private static int nOpt=3;
    private static boolean conected = false;
    private static Semaphore arriveOS = new Semaphore(0);

    public static void main(String[] args)throws Exception{

        // leer nombre teclado
        System.out.println("Introduzca el nombre de su usuario:");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        // crear socket con servidor. Necesitamos conocer ip del servidor y el puerto por el que le llegará el socket.
        Socket sc=new Socket("localhost", Integer.parseInt(args[0])); 
 
        ObjectOutputStream fout = new ObjectOutputStream(sc.getOutputStream());
        ObjectInputStream fin = new ObjectInputStream(sc.getInputStream());

        //crear nuevo thread OyenteServidor para leer el socket
        OyenteServidor OS = new OyenteServidor(fin, fout, arriveOS, user);
        OS.start();

        //enviar mensaje de conexion
        Mensaje mC=new M_Conexion(user);
        fout.writeObject(mC);
        fout.flush();

        //Esperar a mensaje de confirmación de conexión del servidor
        arriveOS.acquire();

        if(conected){ //Se ha podido conectar
            boolean stop = false;
            System.out.println(mensaje);
            while(!stop){
                menu();
                
                try{
                    int opt = scanner.nextInt();
                    if(opt<1 || opt>nOpt){
                        System.out.println("\nNo existe dicha opcion. Vuelva a elegir una:");
                    }
                    else{
                        switch(opt){
                            case 1: //consultar lista de usuarios
                                M_LU mLU=new M_LU();
                                fout.writeObject(mLU);
                                fout.flush();
                                arriveOS.acquire(); //Esperar a que oyenteServidor reciba mensaje de respuesta
                                pintarLista(mensaje);
                                break;
    
                            case 2: //pedir fichero (esta es la que se usa en la parte 1)
                                System.out.println("Introduzca nombre del fichero:");
                                scanner.nextLine();//Coge el intro
                                String fich=scanner.nextLine();
                                M_Pedir_Fich mPf= new M_Pedir_Fich(fich, user);
                                fout.writeObject(mPf);
                                fout.flush();
                                arriveOS.acquire(); //Esperar a que oyenteServidor reciba mensaje de respuesta
                                System.out.println(mensaje);
                                break;
    
                            case 3: //abandonar conexion
                                stop=true;
                                M_Fin mFc= new M_Fin(user);
                                fout.writeObject(mFc);
                                fout.flush();
                                arriveOS.acquire(); //Esperar a que oyenteServidor reciba mensaje de respuesta
                                System.out.println(mensaje);
                                break;
                        }
                        
                    }
                }catch(InputMismatchException e){
                    System.out.println("\nLa opcion ha de ser un numero");
                    scanner.nextLine();
                }  
            }
        }
        else{
            System.out.println(mensaje); 
        }
        OS.join();
    }

    private static void pintarLista(String lista) {
        System.out.println("\nLISTA DE USUARIOS (verde -> conectados, rojo -> desconectados): \n");
        System.out.println(lista);
        System.out.println("------------------------\n");
    }

    public static void menu(){
        System.out.println("\n------ OPCIONES -------\n");
        System.out.println("    1. Consultar lista de usuarios");
        System.out.println("    2. Pedir fichero");
        System.out.println("    3. Salir");
        System.out.println("\n---------------\n");
        System.out.println("Escoja la opcion deseada: ");
    }

    public static void setConected(boolean c){
        conected = c;
    }

    public static void setMensaje(String m){
        mensaje = m;
    }
}
