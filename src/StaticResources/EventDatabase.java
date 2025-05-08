package StaticResources;

import Models.MiscObjects.Event;
import Models.MiscObjects.Room;

import java.util.*;

public class EventDatabase {

    private static final List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public EventDatabase(OrganizersDatabase organizersDatabase, RoomDatabase roomDatabase, CategoryDatabase categoryDatabase) {
        Date date = new GregorianCalendar(124, Calendar.SEPTEMBER, 10).getTime();
        Room room = roomDatabase.getRooms().getFirst();
        Event event = new Event("First Event", date, organizersDatabase.getOrganizers().getFirst(), room, categoryDatabase.getCategories().getFirst());

        room.addEvent(event);
        addEvent(event);

        date = new GregorianCalendar(125, Calendar.FEBRUARY, 6).getTime();
        event = new Event("Second Event", date, organizersDatabase.getOrganizers().get(1), room, categoryDatabase.getCategories().get(1));
        room.addEvent(event);

        room.addEvent(event);
        addEvent(event);

        date = new GregorianCalendar(126, Calendar.MARCH, 13).getTime();
        room = roomDatabase.getRooms().get(1);
        event = new Event("3rd Event", date, organizersDatabase.getOrganizers().getFirst(), room, categoryDatabase.getCategories().get(2));

        room.addEvent(event);
        addEvent(event);

        date = new GregorianCalendar(127, Calendar.JULY, 18).getTime();
        room = roomDatabase.getRooms().get(1);
        event = new Event("Fourth Event", date, organizersDatabase.getOrganizers().get(1), room, categoryDatabase.getCategories().getLast());

        room.addEvent(event);
        addEvent(event);
    }
}
