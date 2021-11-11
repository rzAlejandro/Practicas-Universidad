public class M_Error_Fichero extends Mensaje{

    private String error;

    public M_Error_Fichero(String error){
        super(11);
        this.error = error;
    }

    public String getError() {
        return error;
    }
    
}
