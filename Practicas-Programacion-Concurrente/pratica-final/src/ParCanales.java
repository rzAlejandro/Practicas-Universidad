import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ParCanales {
    
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public ParCanales(ObjectInputStream entrada,ObjectOutputStream salida ){
        this.entrada=entrada;
        this.salida=salida;
    }

    public ParCanales() {
    }

    public ObjectInputStream first(){
        return entrada;
    }

    public ObjectOutputStream second(){
        return salida;
    }
}
