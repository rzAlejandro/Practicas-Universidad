import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class OyenteCliente extends Thread{
    private Socket socket;
    private Monitor monitor;
    private TablaUsuarios tablaUsuarios;
    private TablaInfo tablaInfo;
    private Puerto puerto; //Para ver que nº de puerto ha de pasarle al emisor
    private int id; //id para lock del Puerto.

    public OyenteCliente(Socket si,Monitor monitor, TablaUsuarios tablaU, TablaInfo tablaInfo, Puerto puerto, int id){
        this.socket = si;
        this.monitor = monitor;
        this.tablaUsuarios = tablaU;
        this.tablaInfo = tablaInfo;
        this.puerto = puerto;
        this.id = id; 
        }

    public void run(){
        try{
            ObjectOutputStream fout = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
            while (true){
                Mensaje m =  new Mensaje();
                m = (Mensaje) fin.readObject();
                switch(m.getTipo()){
                    case 0: //le llega mensaje de conexión
                        String id_user = ((M_Conexion) m).getId();
                        monitor.request_read();
                        if(tablaInfo.isRegistered(id_user)){ //Si no esta en usuarios registrados, no se puede conectar
                            if(!tablaInfo.isConected(id_user)){ //Si no se ha conectado nadie con tu nombre, puedes conectarte
                                monitor.release_read();
                                
                                //Escribir info en tabla_Usuarios
                                tablaUsuarios.request_write();
                                tablaUsuarios.addUser(id_user, new ParCanales(fin,fout));
                                tablaUsuarios.release_write();

                                //Poner a true conected en tabla info
                                monitor.request_write();
                                tablaInfo.setConected(id_user);
                                monitor.release_write();
                                System.out.println("El usuario " + id_user + " se ha conectado");
                                //envio mensaje confirmacion conexion fout
                                Mensaje mCx = new M_Conf_Conexion();
                                fout.writeObject(mCx);
                                fout.flush();
                            }
                            else{
                                monitor.release_read();
                                Mensaje mEc = new M_Error_Conexion("\nERROR: EL USUARIO YA SE HA CONECTADO EN OTRO TERMINAL\n");
                                fout.writeObject(mEc);
                                fout.flush();
                            }
                        }
                        else{ // El usuario no está registrado
                            monitor.release_read();
                            Mensaje mEc = new M_Error_Conexion("\nERROR: EL USUARIO NO EXISTE EN NUESTRA BASE DE DATOS\n");
                            fout.writeObject(mEc);
                            fout.flush();
                        }
                        break;

                    case 2: //le llega mensaje lista de usuarios
                        monitor.request_read();
                        //Me veo obligado a clonar el HashMap y todos los arrayLists que contiene para pasar la info.
                        HashMap<String, Boolean> tabla_conectados = new HashMap<String, Boolean> (tablaInfo.getConects()); //Cosas de sockets
                        M_Conf_LU mLu = new M_Conf_LU(clonar(tablaInfo.getInfo()), tabla_conectados); 
                        monitor.release_read();
                    	fout.writeObject(mLu);
                        fout.flush();
                        break;

                    case 4://le llega mensaje de pedir fichero
                        String fich = ((M_Pedir_Fich)m).getFich();
                        String user = ((M_Pedir_Fich)m).getUser();
                        //* buscar usuario que contiene el fichero y obtener fout2
                        monitor.request_read();
                        ArrayList<String> users = tablaInfo.getUsers(fich); //Solo cogemos los que estan conectados
                        monitor.release_read();
                        if(users.size() == 0){//Ningun usuario conectado tiene ese fichero
                            M_Error_Fichero mEf = new M_Error_Fichero("\nERROR: El fichero " + fich + " que desea no existe o no se encuentra disponible en estos momentos");
                            fout.writeObject(mEf);
                            fout.flush();
                        } 
                        else{ 
                            boolean stop = false;
                            for(int i = 0; i < users.size() && !stop; ++i){
                                if(user.equals(users.get(i)))
                                    stop = true;
                            }
                            if(stop){//El usuario ya tenia ese fichero
                                M_Error_Fichero mEf = new M_Error_Fichero("\nERROR: Usted ya tiene ese fichero descargado");
                                fout.writeObject(mEf);
                                fout.flush();
                            }
                            else{
                                 //Modificamos la tabla de info añadiendo al usuario receptor el nuevo fichero
                                monitor.request_write();
                                tablaInfo.addFich(user, fich);
                                monitor.release_write();
                                //Buscamos el usuario que va a emitir el fichero. Cogemos el primero que encontro.
                                tablaUsuarios.request_read();
                                ObjectOutputStream fout2 = tablaUsuarios.getOutStream(users.get(0));
                                tablaUsuarios.release_read();
                                M_Emit_Fich mpf = new M_Emit_Fich(fich, user,puerto.getPuertoF(id));
                                fout2.writeObject(mpf);
                                fout2.flush();
                            }
                        }
                        break;

                    case 6: // el emisor del fichero esta preparado para enviar
                        //Buscar el usuario que va a recibir el fichero. Puede ser que se hubiera desconectado sin recibir la descarga
                        tablaUsuarios.request_read();
                        ObjectOutputStream fout2 = tablaUsuarios.getOutStream(((M_Prep_CS)m).getReceptor());
                        tablaUsuarios.release_read();

                        if (fout2 == null){//El usuario receptor se desconecto
                            M_Error_Descarga mpcs = new M_Error_Descarga();
                            fout.writeObject(mpcs);
                            fout.flush();
                        }
                        else{
                            M_Prep_SC mpcs = new M_Prep_SC(((M_Prep_CS)m).getdIP(), ((M_Prep_CS)m).getPuerto());
                            fout2.writeObject(mpcs);
                            fout2.flush();
                        }
                        break;

                    case 8: //le llega mensaje de fin de conexion
                        // * eliminar informacion del usuario (en las tablas)
                        String id_user2 = ((M_Fin)m).getId();
                    	monitor.request_write();
                    	tablaInfo.setDisconected(id_user2); //Marca como desconectado de la tabla de info
                    	monitor.release_write();
                    	tablaUsuarios.request_write();
                    	tablaUsuarios.deleteUser(id_user2); // Elimina la entrada de user en esta tabla (user, canales)                   	
                    	tablaUsuarios.release_write();
                        System.out.println("\nEl usuario " + id_user2 + " se ha desconectado");
                        M_Conf_Fin  mcCfx = new M_Conf_Fin();
                        fout.writeObject(mcCfx);
                        fout.flush();
                        break; 
                }
            }
        }

        catch (ClassNotFoundException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<String>> clonar(HashMap<String, ArrayList<String>> tInit){
        HashMap<String, ArrayList<String>> s = new HashMap<String, ArrayList<String>>();
        for(String k : tInit.keySet()){
            ArrayList<String> a = new ArrayList<String>(tInit.get(k));
            s.put(k,a);
        }
        return s;
    }

}
