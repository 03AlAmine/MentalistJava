public class Reseau extends AdresseIP {
    private int masque;
    private String adresseDebut;
    private String adresseFin;

    public Reseau(String adresse, int masque) throws InvalidIPException {
        super(adresse);
        this.masque = masque;
        calculerPlageAdresse();
    }

    public void calculerPlageAdresse() {
        int bitsHost = 32 - masque;
        int nombreIPs = (int) Math.pow(2, bitsHost);

        int baseAdresse = 0;
        for (int i = 0; i < 4; i++) {
            baseAdresse = (baseAdresse << 8) | octets[i];
        }

        int masqueReseau = ~((1 << bitsHost) - 1);
        int adresseBase = baseAdresse & masqueReseau;

        adresseDebut = convertirIntEnIP(adresseBase);
        adresseFin = convertirIntEnIP(adresseBase + nombreIPs - 1);
    }

    private String convertirIntEnIP(int adresse) {
        return ((adresse >> 24) & 0xFF) + "." +
               ((adresse >> 16) & 0xFF) + "." +
               ((adresse >> 8) & 0xFF) + "." +
               (adresse & 0xFF);
    }

    public String getAdresseDebut() {
        return adresseDebut;
    }

    public String getAdresseFin() {
        return adresseFin;
    }

    public int getMasqueEnBits() {
        return masque;
    }
}
