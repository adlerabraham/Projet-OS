import Threads.*;
import Hardware.CPU;

public class Init {
    public static void main(String[] args) {
        CPU myCPU = new CPU();

        new EventsThread(myCPU);
        new MainThread(myCPU);
    }
}