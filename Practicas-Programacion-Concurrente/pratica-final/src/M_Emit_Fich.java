public class M_Emit_Fich extends Mensaje {

	private String fich;
	private String receptor;
	private int puerto;

	public M_Emit_Fich(String fich, String receptor, int puerto) {
		super(5);
		this.fich = fich;
		this.receptor = receptor;
		this.puerto = puerto;
	}

	public String getFich(){
		return fich;
	}

	public String getReceptor(){
		return receptor;
	}

	public int getPuerto(){
		return puerto;
	}
	
}
