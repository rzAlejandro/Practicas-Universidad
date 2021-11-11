public class M_Error_Conexion extends Mensaje{
    
    private String error;

    public M_Error_Conexion(String error){
        super(10);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
