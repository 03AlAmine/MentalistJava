import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateurReseauSwingApp extends JFrame {
    private JTextField ipField, masqueField; // Champs de saisie pour IP et masque
    private JTextArea resultArea; // Zone de texte pour les résultats
    private JButton calculerButton, historiqueButton; // Boutons
    private HistoriqueCalculs historique; // Instance de gestion de l'historique

    public CalculateurReseauSwingApp() {
        // Initialisation
        setTitle("Calculateur d'Adresse Réseau");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        historique = new HistoriqueCalculs(); // Gestionnaire d'historique

        // === Zone supérieure : Champs de saisie et boutons ===
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Adresse IP :"));
        ipField = new JTextField();
        inputPanel.add(ipField);

        inputPanel.add(new JLabel("Masque (en bits) :"));
        masqueField = new JTextField();
        inputPanel.add(masqueField);

        calculerButton = new JButton("Calculer");
        historiqueButton = new JButton("Historique");
        inputPanel.add(calculerButton);
        inputPanel.add(historiqueButton);

        add(inputPanel, BorderLayout.NORTH);

        // === Zone centrale : Résultats ===
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // === Actions des boutons ===
        calculerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculerReseau();
            }
        });

        historiqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherHistorique();
            }
        });
    }

    private void calculerReseau() {
        try {
            String adresse = ipField.getText();
            int masque = Integer.parseInt(masqueField.getText());

            Reseau reseau = new Reseau(adresse, masque);
            String resultat = "Classe : " + reseau.getClasse() + "\n" +
                              "Plage : " + reseau.getAdresseDebut() + " - " + reseau.getAdresseFin();

            resultArea.setText(resultat);

            // Sauvegarder dans l'historique
            historique.ajouterCalcul("IP : " + adresse + ", Masque : " + masque + ", Plage : " +
                                      reseau.getAdresseDebut() + " - " + reseau.getAdresseFin());
            historique.sauvegarderHistorique("historique_calculs.txt");

        } catch (Exception ex) {
            resultArea.setText("Erreur : " + ex.getMessage());
        }
    }

    private void afficherHistorique() {
        try {
            StringBuilder contenu = new StringBuilder();
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File("historique_calculs.txt"));

            while (scanner.hasNextLine()) {
                contenu.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            resultArea.setText(contenu.toString());
        } catch (Exception ex) {
            resultArea.setText("Erreur lors de la lecture de l'historique : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculateurReseauSwingApp app = new CalculateurReseauSwingApp();
            app.setVisible(true);
        });
    }
}
