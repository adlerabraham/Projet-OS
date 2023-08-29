package Scheduler;

import java.util.Queue;
import java.util.LinkedList;
import Processes.PCB;

public class ShortTermShceduler {
    private static Queue<PCB> waitingList = new LinkedList<>();

    public ShortTermShceduler() {

    }

    public static void putProcessOnWait(PCB pcb) {
        waitingList.add(pcb);
    }

    public static PCB retreiveProcess() {
        return waitingList.poll();
    }
}
