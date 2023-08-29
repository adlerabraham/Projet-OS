import Processes.Process;
import Scheduler.ShortTermShceduler;
import Threads.*;
import java.util.*;
import Hardware.CPU;
import Hardware.Ram;

import Hardware.CPU;

public class Init {
    public static void main(String[] args) {
        Process P = new Process(1, 5);
        CPU CPU1 = new CPU();
        ShortTermShceduler stscheduler = new ShortTermShceduler();

        System.out.println("Process ID:" + P.getProcessID());
        System.out.println("Process state:" + P.getState());

        CPU.execute(P);

        System.out.println("Process ID:" + P.getProcessID());
        System.out.println("Process state:" + P.getState());

    }
}