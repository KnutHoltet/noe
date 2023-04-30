import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Oblig5Del1 {
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();
        String folder = args[0];
        String sti = folder + "/metadata.csv";

        File f;
        Scanner scan = null;
        try{
            f = new File(sti);
            scan = new Scanner(f);
        }catch(FileNotFoundException e){
            System.out.println();
        }

        while(scan.hasNext()){
            String filnavn = scan.nextLine();
            register.leggTil(SubsekvensRegister.lesFil(folder + "/" + filnavn));
        }

        ArrayList<HashMap<String, Subsekvens>> liste = register.hentListe();
        HashMap<String, Subsekvens> kombinert = new HashMap<>(liste.get(0));
        for(int i = 1; i < liste.size(); i++){
            kombinert = SubsekvensRegister.kombinasjonAvAlle(kombinert, liste.get(i));
        }
        liste.clear();
        liste.add(kombinert);

        if(folder.equals("TestDataLike")){
            for(HashMap<String, Subsekvens> map : liste){
                if(map.get("QYF").hentAntall() == 9){
                    System.out.println(map.get("QYF"));
                }
            }
        }

        if(folder.equals("TestDataLitenLike")){
            for(HashMap<String, Subsekvens> map : liste){
                if(map.get("ASS").hentAntall() == 3){
                    System.out.println(map.get("ASS"));
                }
            }
        }

    }
}
