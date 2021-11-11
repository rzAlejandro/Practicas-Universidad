public class M_Pedir_Fich extends Mensaje{

    private String fich;
    private String user;

    public M_Pedir_Fich(String fich, String user){
        super(4);
        this.fich=fich;
        this.user = user;
    }

	public String getFich() {
		return fich;
	}

    public String getUser() {
        return user;
    }
   
}
