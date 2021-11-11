import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread{

    private String user;
    private int puerto;
    private String fich;

    public Emisor(String user, int puerto, String fich){
        this.user = user;
        this.puerto = puerto;
        this.fich = fich;
    }

    public void run(){
        try {
            
            ServerSocket ss = new ServerSocket(puerto);
            Socket si = ss.accept();
            
            DataInputStream input;
            BufferedInputStream bis;
            BufferedOutputStream bos;
            int in;
            byte[] byteArray;
            try{
                File localFile = new File( user + "/" + fich );
                bis = new BufferedInputStream(new FileInputStream(localFile));
                bos = new BufferedOutputStream(si.getOutputStream());
    
                //enviamos el nombre del archivo            
                DataOutputStream dos=new DataOutputStream(si.getOutputStream());
                dos.writeUTF(localFile.getName());//getName solo coge el nombre, no la ruta
    
                byteArray = new byte[8192];
                while ((in = bis.read(byteArray)) != -1){
                    bos.write(byteArray,0,in);
                }
    
                bis.close();
                bos.close();
    
            }catch ( Exception e ) {
                System.err.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
