import Processes.Process;
import Scheduler.LongTermSheduler;
import Scheduler.ShortTermShceduler;
import Threads.*;
import java.util.*;
import Hardware.CPU;
import Hardware.Ram;

public class Init {
    public static void main(String[] args) {
        Process P = new Process(1, "Count", 5);
        Process P1 = new Process(2, "increment", 3);
        Process P2 = new Process(3, "decrement", 8);

        CPU myCPU = new CPU();
        Ram myRam = new Ram();
        ShortTermShceduler stscheduler = new ShortTermShceduler();

        // System.out.println("Process name:" + P.getProcessName());
        // System.out.println("Process state:" + P.getState());

        System.out.println("execution 1");

        if (LongTermSheduler.allocateMemorySpace(P1)) {
            for (int number : P1.getAllocatedMemory()) {
                System.out.println(number);
            }
            CPU.execute(P1);
        }

        System.out.println("execution 2");

        if (LongTermSheduler.allocateMemorySpace(P)) {
            for (int number : P.getAllocatedMemory()) {
                System.out.println(number);
            }
            CPU.execute(P);
        }

        System.out.println("execution 3");

        if (LongTermSheduler.allocateMemorySpace(P2)) {
            for (int number : P2.getAllocatedMemory()) {
                System.out.println(number);
            }
            CPU.execute(P2);
        }

        // System.out.println("Process name:" + P.getProcessName());
        // System.out.println("Process state:" + P.getState());

    }
}