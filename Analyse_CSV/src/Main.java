import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String cheminFichierCSV = "CSV/test2.csv";
        String cheminFichierCSV = "CSV/test3.csv";

        try {
            AnalyseCSV analyseur = new AnalyseCSV();
            analyseur.analyserCSV(cheminFichierCSV);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
