package settings;

import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static int generateRandomNumber(int numb) {
        return random.nextInt(numb) + 1;
    }
}
