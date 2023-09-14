package utilities;

public class Event {
    private int eventNumber;
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

    public enum EventType {
        INTERRUPT,
        CREATION
    }

}
