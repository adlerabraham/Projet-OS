package Threads;

import Hardware.CPU;
import utilities.Event;
import utilities.NumberGenerator;

public class EventsThread implements Runnable {

    private CPU cpuInstance;

    public EventsThread(CPU cpuParam) {
        cpuInstance = cpuParam;
        Thread t = new Thread(this, "EventsThread");
        t.start();
    }

    @Override
    public void run() {
        int value;
        while (true) {
            value = NumberGenerator.generateNumber(6);

            if (value == 0 || value == 1) {
                cpuInstance.setCpuEvent(value, true, Event.EventType.CREATION);
            } else if (value == 2) {
                cpuInstance.setCpuEvent(value, true, Event.EventType.INTERRUPT);
            } else if (value == 3) {
                cpuInstance.setCpuEvent(value, false, Event.EventType.INTERRUPT);
            } else if (value == 4) {
                cpuInstance.setCpuEvent(value, false, Event.EventType.INTERRUPT);
            }

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
