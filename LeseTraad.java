import java.util.HashMap;

public class LeseTraad implements Runnable{
    Monitor1 monitor;
    String fil;
    public LeseTraad(Monitor1 monitor, String fil){
        this.monitor = monitor;
        this.fil = fil;
    }

    @Override
    public void run(){
        HashMap<String, Subsekvens> map = monitor.lesFraFil(fil);
        monitor.leggTil(map);
    }
}
