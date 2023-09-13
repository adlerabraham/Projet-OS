package Threads;

import Hardware.CPU;
import Hardware.Ram;
import Scheduler.ShortTermShceduler;
import utilities.Event;
import utilities.Event.EventType;

public class MainThread implements Runnable {

    private CPU cpuInstance;

    public MainThread(CPU cpuParam) {
        this.cpuInstance = cpuParam;
        Thread t = new Thread(this, "MainThread");
        t.start();
    }

    @Override
    public void run() {
        new Ram();
        new ShortTermShceduler();

        while (true) {
            cpuInstance.HandleEvent();

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
