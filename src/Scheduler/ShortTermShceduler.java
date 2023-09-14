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
        System.out.println("Le processus " + pcb.getProcessName() + " est en attente a l'etape " +
                pcb.getCurrentStep() + "/" + pcb.getExecutionTime());
    }

    public static PCB retreiveProcess() {
        return waitingList.poll();
    }
}
