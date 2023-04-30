import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class SubsekvensRegister {

    public ArrayList<HashMap<String, Subsekvens>> SubsekvensRegisterListe = new ArrayList<>();

    public void leggTil (HashMap<String, Subsekvens> hash) {
        SubsekvensRegisterListe.add(hash);
    }

    public HashMap<String, Subsekvens> hent() {
        return SubsekvensRegisterListe.get(0);
    }

    public int lengde() {
        return SubsekvensRegisterListe.size();
    }


    public static HashMap<String, Subsekvens> lesFil(String path){
        //Denne metoden skal returnere en hashmap :)

        HashMap<String, Subsekvens> mapFraFil = new HashMap<>();
        File fil;
        Scanner scan = null;
        try{
            fil = new File(path);
            scan = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet ;/ ");
        }

        while(scan.hasNext()){
            String sekvens = scan.nextLine();
            while(sekvens.length() >= 3){
                String subSekvensString = sekvens.substring(0,3);
                sekvens = sekvens.substring(1);
                Subsekvens subsekvensObjekt = new Subsekvens(subSekvensString);

                mapFraFil.put(subSekvensString, subsekvensObjekt);
            }
        }
        return mapFraFil;
    }

    //skal lage en metode som slaar sammen alle hashmappene fra innlesningsfilen
    public static HashMap<String, Subsekvens> kombinasjonAvAlle(HashMap<String, Subsekvens> map, HashMap<String, Subsekvens> map2){
        for(String key : map2.keySet()){
            if(map.containsKey(key)){
                Subsekvens fraMap = map.get(key);
                Subsekvens fraMap2 = map2.get(key);
                int antallFraMap2 = fraMap2.hentAntall();
                fraMap.endreAntall(antallFraMap2);
            }

            //hvis ikke hashmappet eksisterer
            else{
                map.put(key, map2.get(key));
            }
        }
        return map;
    }

    public ArrayList<HashMap<String, Subsekvens>> hentListe(){
        return SubsekvensRegisterListe;
    }
}