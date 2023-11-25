package sg.com.practice.ssf.workshop13.model;
import java.security.SecureRandom;

public class Idgenerator {

    private static String Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int sizelength = 8;

    public static String generateUniqueId() {
        StringBuilder idBuilder = new StringBuilder(sizelength);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < sizelength; i++) {
            int randomIndex = random.nextInt(Characters.length());
            idBuilder.append(Characters.charAt(randomIndex));
        }

        return idBuilder.toString();
    }
}
