import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
OPPGAVE 6
 */

public class Monitor1 {
    SubsekvensRegister register;
    public Monitor1(SubsekvensRegister register){
        this.register = register;
    }
    Lock laas = new ReentrantLock();

    public void leggTil(HashMap<String, Subsekvens> map){
        laas.lock();
        try{
            register.leggTil(map);
        }finally{
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> taUt(){
        return register.hent();
    }

    public HashMap<String, Subsekvens> lesFraFil(String fil){
        laas.lock();
        try{
            return SubsekvensRegister.lesFil(fil);
        }finally{
            laas.unlock();
        }
    }

    public int antHashMaps(){
        laas.lock();
        try{
            return register.lengde();
        }finally{
            laas.unlock();
        }
    }

    public ArrayList<HashMap<String, Subsekvens>> hentListe(){
        laas.lock();
        try{
            return register.hentListe();
        }finally{
            laas.unlock();
        }
    }
}
