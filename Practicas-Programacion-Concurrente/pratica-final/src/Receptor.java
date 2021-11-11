import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class Receptor extends Thread{

    private String user;
    private String dIP;
    private int puerto;

    public Receptor(String user, String dIP, int puerto) {
        this.user = user;
        this.dIP = dIP;
        this.puerto = puerto;
    }

    public void run(){
        try {
            Socket sc=new Socket(dIP,puerto);
            
            BufferedInputStream bis;
            BufferedOutputStream bos;
            byte[] receivedData;
            int in;
            String file;
            receivedData = new byte[1024];
                bis = new BufferedInputStream(sc.getInputStream());
                DataInputStream dis=new DataInputStream(sc.getInputStream());

                //recibimos el nombre del fichero
                file = dis.readUTF();
                file = user + "/" + file; //IMPORTANTE, ejecutando desde dentro de src para poner esta ruta simplificada.

                bos = new BufferedOutputStream(new FileOutputStream(file));
                while ((in = bis.read(receivedData)) != -1){
                    bos.write(receivedData,0,in);
                }
                bos.close();
                dis.close();         

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
}
