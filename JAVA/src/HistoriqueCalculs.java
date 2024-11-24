import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueCalculs {
    private List<String> historique = new ArrayList<>(); // Liste pour stocker les calculs

    // Ajouter un calcul à l'historique
    public void ajouterCalcul(String calcul) {
        historique.add(calcul);
    }

    // Sauvegarder l'historique dans un fichier
    public void sauvegarderHistorique(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (String calcul : historique) {
                writer.write(calcul);
                writer.newLine();
            }
            System.out.println("Historique sauvegardé dans " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de l'historique : " + e.getMessage());
        }
    }

    // Charger et afficher l'historique
    public static void consulterHistorique(String nomFichier) {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(nomFichier))) {
            System.out.println("=== Historique des Calculs ===");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture de l'historique : " + e.getMessage());
        }
    }
}
