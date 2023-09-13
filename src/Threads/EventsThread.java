package Threads;

import utilities.Event;
import utilities.NumberGenerator;

public class EventsThread implements Runnable {

    private Event E;

    public EventsThread(Event e) {
        E = e;
    }

    @Override
    public void run() {
        int value;
        while (true) {
            value = NumberGenerator.generateNumber(6);

            if (value == 0 || value == 1) {
                E.setEventNumber(value);
                E.setEventType(Event.EventType.CREATION);
                E.setPriority(true);
            } else if (value == 2) {
                E.setEventNumber(value);
                E.setEventType(Event.EventType.INTERRUPT);
                E.setPriority(true);
            } else if (value == 3) {
                E.setEventNumber(value);
                E.setEventType(Event.EventType.INTERRUPT);
                E.setPriority(false);
            } else {
                E.setEventNumber(value);
                E.setEventType(Event.EventType.INTERRUPT);
                E.setPriority(false);
            }
        }
    }

}
