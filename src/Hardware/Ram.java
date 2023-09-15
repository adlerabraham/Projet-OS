package Hardware;

public class Ram {
    private int[][] memory;
    private static Frame[] frames;

    public Ram() {
        memory = new int[1024][1024];
        frames = new Frame[8192];
        createFrames();
    }

    public void createFrames() {
        boolean value = false;
        // Creer un tableau de frame non utilise
        for (int i = 0; i < 8192; i++) {
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

        // verifie qu'on n'a pas depasser la taille du tableau de frame ou qu'on n'a pas
        // encore atteint le
        // nombre de frames contigus desires.
        while (i < 8192 && numberOfContiguousFrames < numberOfInstructions) {
            if (frames[i].getFrameState() == false) { // si le frame n'est pas utilise alors le compteur augmente

                numberOfContiguousFrames++;
            } else {
                numberOfContiguousFrames = 0; // sinon on reinitialise le compteur
            }

            i++;
        }

        if (numberOfContiguousFrames == numberOfInstructions) {

            contiguousSpaceStart = i - numberOfInstructions; // si l'espace contigue est trouve la methode
            for (int j = contiguousSpaceStart; j < i; j++) { // marque les frames trouves comme occupes
                frames[j].setFrameState(true); // et retourne l'adresse de depart
            }
            return contiguousSpaceStart;
        } else {
            return -1; // sinon elle retourne -1
        }

    }

    public static void freeFrame(int index) {
        frames[index].setFrameState(false);
    }

}
