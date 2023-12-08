package sg.com.practice.ssf.workshop13.model;
import java.security.SecureRandom; // allows more bytes than Random

public class Idgenerator {

    private static String Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int sizelength = 8;

    public static String generateUniqueId() {
        StringBuilder idBuilder = new StringBuilder(sizelength);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < sizelength; i++) {
            int randomIndex = random.nextInt(Characters.length()); // it will randomly generate an index among the string called Characters by sizelength times
            idBuilder.append(Characters.charAt(randomIndex)); // print out the char at the randomIndex generated and put them together to a string
        }

        return idBuilder.toString();
    }
}
