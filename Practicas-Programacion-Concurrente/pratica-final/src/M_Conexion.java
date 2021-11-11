public class M_Conexion extends Mensaje{

    private String id;
    
    public M_Conexion(String id){
        super(0);
        this.id = id;
    }

    public String getId(){
        return id;
    }
}