package Hardware;

public class Ram {
    private int[][] memory;
    private static Frame[] frames;

    public Ram() {
        memory = new int[1024][1024];
        frames = new Frame[6241];
        createFrames();
    }

    public void createFrames() {
        boolean value = false;
        for (int i = 0; i < 6241; i++) {
            Frame f = new Frame(value);
            frames[i] = f;
        }
    }

    public Frame[] getFramesTable() {
        return frames;
    }

    public static int contiguousEmptyFrame(int numberOfInstructions) {
        int numberOfContiguousFrames = 0;
        int contiguousSpaceStart;
        int i = 0;

        while (i < 6241 && numberOfContiguousFrames < numberOfInstructions) {
            if (frames[i].getFrameState() == false) { // si le frame n'est pas utilise alors le compteur augmente

                numberOfContiguousFrames++;
            } else {
                numberOfContiguousFrames = 0; // sinon on reinitialise le compteur
            }

            i++;
        }

        if (numberOfContiguousFrames == numberOfInstructions) {

            contiguousSpaceStart = i - numberOfInstructions; // si l'espace contigue est trouve la methode
            for (int j = contiguousSpaceStart; j < i; j++) {
                frames[j].setFrameState(true);
            }
            return contiguousSpaceStart; // retourne l'adresse de depart
        } else {
            return -1; // sinon elle retourne -1
        }

    }

    public static void freeFrame(int index) {
        frames[index].setFrameState(false);
    }

}
