public class M_Prep_CS extends Mensaje {
	
	private String dIP;
	private int puerto;
	private String receptor;

	public M_Prep_CS(String dIP, int puerto, String receptor){
		super(6);
		this.dIP = dIP;
		this.puerto = puerto;
		this.receptor = receptor;
	}

    public String getdIP() {
        return dIP;
    }

	public int getPuerto() {
		return puerto;
	}

	public String getReceptor(){
		return receptor;
	}
}
