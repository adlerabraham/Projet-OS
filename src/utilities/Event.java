package utilities;

public class Event {
    private int eventNumber;
    private boolean masquable;
    private EventType type;

    public Event(int number, boolean masquable, EventType type) {
        eventNumber = number;
        this.masquable = masquable;
        this.type = type;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int number) {
        eventNumber = number;
    }

    public boolean getPriority() {
        return masquable;
    }

    public void setPriority(boolean priority) {
        masquable = priority;
    }

    public EventType getEventType() {
        return type;
    }

    public void setEventType(EventType type) {
        this.type = type;
    }

    public enum EventType {
        INTERRUPT,
        CREATION
    }
}
