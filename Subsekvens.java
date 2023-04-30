public class Subsekvens {

    public final String subsekvens;
    private int antall;

    public Subsekvens(String subsekvens) {
        this.subsekvens = subsekvens;
        antall = 1;
    }

    public int hentAntall(){
        return antall;
    }

    public void endreAntall(int endring){
        antall += endring;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + "," + antall + ")";
    }
}
