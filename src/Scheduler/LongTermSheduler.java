package Scheduler;

import java.util.LinkedList;
import java.util.Queue;

public class LongTermSheduler {

    private static Queue<Process> readyQueue = new LinkedList<>();

    public LongTermSheduler() {

    }

    public static void addProcess(Process p) {

        readyQueue.add(p);
    }

    public static Process retreiveProcess() {

        return readyQueue.poll();
    }
}
