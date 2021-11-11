import java.io.Serializable;

public class Puerto implements Serializable{

    public LockRompeEmpates lockF = new LockRompeEmpates(100);
    //public LockRompeEmpates lockC = new LockRompeEmpates(100); //Como maximo 100 entidades conectadas a la vez.
    //public int puertoC;
    public int puertoF;

    public Puerto(int j) {
        //puertoC = i;
        puertoF = j;
    }

    /*
    public int getPuerto(int id) {
        //Garantizar exclusion mutua en el lock, pues pueden querer acceder a el varios procesos a la vez.
        //lockC.takeLock(id);
        int x = puertoC;
        //lockC.releaseLock(id);
        return x;
    }

    public void incrementarPuerto(int id){
        //lockF.takeLock(id);
        puertoC++;
        //lockC.releaseLock(id);
    }
    */
    public int getPuertoF(int id){
        lockF.takeLock(id);
        int x = puertoF;
        puertoF++;
        lockF.releaseLock(id);
        return x;
    }
}
