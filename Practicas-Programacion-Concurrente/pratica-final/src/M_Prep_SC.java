public class M_Prep_SC extends Mensaje{

	private String dIP;
	private int puerto;
	
	public M_Prep_SC(String dIP, int puerto){
		super(7);
		this.puerto = puerto;
		this.dIP = dIP;
	}

	public String getdIP() {
		return dIP;
	}

    public int getPuerto() {
        return puerto;
    }
}
