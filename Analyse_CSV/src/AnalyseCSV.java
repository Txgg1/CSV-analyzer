import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AnalyseCSV {

    public List<List<String>> analyserCSV(String cheminFichierCSV) throws IOException {
        List<List<String>> resultat = new ArrayList<>();
        List<String> lignes = Files.readAllLines(Paths.get(cheminFichierCSV));

        if (!lignes.isEmpty() && lignes.get(0).startsWith("\uFEFF")) {
            lignes.set(0, lignes.get(0).substring(1));
        }

        for (String ligne : lignes) {
            resultat.add(analyserLigneCSV(ligne));
        }

        afficherFormatResultat(resultat);
        return resultat;
    }

    private List<String> analyserLigneCSV(String ligne) {
        List<String> champs = new ArrayList<>();
        StringBuilder champCourant = new StringBuilder();
        boolean dansChampCite = false;

        for (int index = 0; index < ligne.length(); index++) {
            char caractereCourant = ligne.charAt(index);

            if (caractereCourant == ',') {
                if (!dansChampCite) {
                    champs.add(champCourant.toString());
                    champCourant.setLength(0);
                } else {
                    champCourant.append(caractereCourant);
                }
            } else if (caractereCourant == '"') {
                dansChampCite = !dansChampCite;

                if (dansChampCite && index + 1 < ligne.length() && ligne.charAt(index + 1) == '"') {
                    champCourant.append('"');
                    index++;
                }
            } else {
                champCourant.append(caractereCourant);
            }
        }

        champs.add(champCourant.toString());
        return champs;
    }

    private void afficherFormatResultat(List<List<String>> resultat) {
        System.out.print("[");
        for (int i = 0; i < resultat.size(); i++) {
            System.out.print("[");
            List<String> ligne = resultat.get(i);
            for (int j = 0; j < ligne.size(); j++) {
                System.out.print("'" + ligne.get(j) + "'");
                if (j < ligne.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i < resultat.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }
}
