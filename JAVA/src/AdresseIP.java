import java.util.regex.Pattern;

public class AdresseIP {
    protected int[] octets = new int[4];
    protected char classe;

    public AdresseIP(String adresse) throws InvalidIPException {
        if (!validerAdresse(adresse)) {
            throw new InvalidIPException("Adresse IP invalide !");
        }
        String[] parts = adresse.split("\\.");
        for (int i = 0; i < 4; i++) {
            octets[i] = Integer.parseInt(parts[i]);
        }
        classe = getClasse();
    }

    private boolean validerAdresse(String adresse) {
        String ipPattern = "^(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})(\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})){3}$";
        return Pattern.matches(ipPattern, adresse);
    }

    public char getClasse() {
        if (octets[0] <= 127) return 'A';
        if (octets[0] <= 191) return 'B';
        if (octets[0] <= 223) return 'C';
        if (octets[0] <= 239) return 'D';
        return 'E';
    }

    @Override
    public String toString() {
        return octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3];
    }
}

class InvalidIPException extends Exception {
    public InvalidIPException(String message) {
        super(message);
    }
}
