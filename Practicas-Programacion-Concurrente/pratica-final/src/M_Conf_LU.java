import java.util.ArrayList;
import java.util.HashMap;

public class M_Conf_LU extends Mensaje{

	private HashMap<String, ArrayList<String>> tabla_info;
	private HashMap<String, Boolean> tabla_conectados;
	
	public M_Conf_LU(){
		super();
	}
	public M_Conf_LU(HashMap<String, ArrayList<String>> tabla_info, HashMap<String, Boolean> tabla_conectados) {
		super(3);
		this.tabla_info = tabla_info;
		this.tabla_conectados = tabla_conectados;
	}

	public HashMap<String, ArrayList<String>> getInfo() {
		return tabla_info;
	}

	public HashMap<String, Boolean> getConects() {
		return tabla_conectados;
	}

	
}
