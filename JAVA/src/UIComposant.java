import javax.swing.*;

public abstract class UIComposant {
    protected JFrame frame;
    protected JTextField champIP;
    protected JTextField champMasque;
    protected JTextArea zoneResultats;

    public abstract void initialiserUI();
    public abstract void mettreAJourResultats(String resultats);
}
