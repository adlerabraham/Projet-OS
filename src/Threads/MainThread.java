package Threads;

import Hardware.CPU;
import Hardware.Ram;
import Scheduler.ShortTermShceduler;
import utilities.Event;

public class MainThread implements Runnable {

    private Event E;

    public MainThread(Event e) {
        this.E = e;
    }

    @Override
    public void run() {
        CPU myCPU = new CPU();
        Ram myRam = new Ram();
        ShortTermShceduler stscheduler = new ShortTermShceduler();
    }
}
