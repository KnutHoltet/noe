import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Oblig5Del2A{
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();
        Monitor1 monitor = new Monitor1(register);
        ArrayList<Thread> leseTraader = new ArrayList<>();
        String folder = args[0];
        String path = folder + "/metadata.csv";
        File f = null;
        Scanner scan = null;
        try{
            f = new File(path);
            scan = new Scanner(f);
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet ;/ ");
        }

        while(scan.hasNext()){
            LeseTraad lese = new LeseTraad(monitor, folder + "/" + scan.nextLine());
            Thread t = new Thread(lese);

            leseTraader.add(t);
        }

        for(Thread traad : leseTraader){
            traad.start();
        }

        try{
            for(Thread traad2 : leseTraader){
                traad2.join();
            }
        }catch(InterruptedException e){
            System.out.println("Noe gikk galt " + e);
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
