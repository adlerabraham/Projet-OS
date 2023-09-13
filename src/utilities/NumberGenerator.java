package utilities;

import java.util.Random;

public class NumberGenerator {

    public static int generateNumber(int maxValue) {
        Random rand = new Random();
        return (rand.nextInt(maxValue));
    }
}
