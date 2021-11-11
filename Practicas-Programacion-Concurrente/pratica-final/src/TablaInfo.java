import java.util.ArrayList;
import java.util.HashMap;

public class TablaInfo {

    private HashMap <String, ArrayList<String>> tabla_info; //usuarios registrados con sus ficheros
    private HashMap <String, Boolean> tabla_conectados;

    public TablaInfo(HashMap <String, ArrayList<String>> tabla_info, HashMap <String, Boolean> tabla_conectados){
        this.tabla_info = tabla_info;
        this.tabla_conectados = tabla_conectados;
    }

    public HashMap <String, ArrayList<String>> getInfo() {
		return tabla_info;
	}

    public HashMap<String, Boolean> getConects() {
        return tabla_conectados;
    }

    public boolean isRegistered(String id){
        return tabla_info.containsKey(id);
    }

    public boolean isConected(String user){
        return tabla_conectados.get(user);
    }

    public void setConected(String user){
        tabla_conectados.put(user,true);
    }

    public void setDisconected(String user){
        tabla_conectados.put(user,false);
    }
	
    public ArrayList<String> getUsers(String fichero) {
        ArrayList<String> s = new ArrayList<String>();
        for(String key : tabla_info.keySet()){
            if(isConected(key) && isFich(key,fichero))
                s.add(key);
        }
        return s;
    }

    public boolean isFich(String user, String fich){
        ArrayList<String> ficheros = tabla_info.get(user);
        boolean stop = false;
        for(int i = 0; i < ficheros.size() && !stop;++i){
            if(ficheros.get(i).equals(fich))
                stop = true;
        }
        return stop;
    }

    public void addFich(String user, String fich) {
        tabla_info.get(user).add(fich);
    }

    
   
}
