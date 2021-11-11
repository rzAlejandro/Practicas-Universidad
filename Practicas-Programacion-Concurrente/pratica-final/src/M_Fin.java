public class M_Fin extends Mensaje{

    private String id;

    public M_Fin(String id) {
		super(8);
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
}
