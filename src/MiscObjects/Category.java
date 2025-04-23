package MiscObjects;

import java.util.List;

public class Category {
    String name;
    List<Event> events;

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    Category(String name, List<Event> events) {
        setName(name);
        setEvents(events);
    }
}
