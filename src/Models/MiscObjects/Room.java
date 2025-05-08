package Models.MiscObjects;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Room {
    String name;
    List<Time> availableTimes = new ArrayList<>();
    List<Event> events = new ArrayList<>();

    public String getRoomName() {
        return name;
    }

    public List<Time> getAvailableTimes() {
        return availableTimes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setAvailableTimes(List<Time> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public void addAvailableTime(Time time){
        availableTimes.add(time);
    }

    public void removeAvailableTime(Time time){
        availableTimes.remove(time);
    }

    void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public Room(String name, List<Time> availableTimes, List<Event> events) {
        setName(name);
        setAvailableTimes(availableTimes);
        setEvents(events);
    }
}
