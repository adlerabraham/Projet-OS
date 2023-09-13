package utilities;

public class Event {
    private int eventNumber;
    private boolean masquable;
    private EventType type;

    public EventType getEventType() {
        return type;
    }

    public void setEvenType(EventType eType) {
        type = eType;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int num) {
        eventNumber = num;
    }

    public boolean getPriority() {
        return masquable;
    }

    public void setPriority(boolean priority) {
        masquable = priority;
    }

    public enum EventType {
        INTERRUPT,
        CREATION
    }

    // public synchronized Event getEvent() {
    // while (!valueSet) { // le thread principal attend que l'interrution soit
    // genere
    // try {
    // wait();
    // } catch (Exception e) {

    // }
    // }
    // Event e = new Event();
    // e.eventNumber = eventNumber;
    // e.masquable = masquable;
    // e.type = type;

    // valueSet = false;
    // notify();
    // return e;
    // }

    // public synchronized void setEvent(int number, boolean priority, EventType
    // eType) {
    // while (valueSet) { // Le generateur d'evenement attend que l'interruption
    // soit recuperer
    // try {
    // wait();
    // } catch (Exception e) {
    // //
    // }
    // }
    // eventNumber = number;
    // masquable = priority;
    // type = eType;

    // valueSet = true;
    // notify();
    // }

}
