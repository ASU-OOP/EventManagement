package StaticResources;

import MiscObjects.Event;
import MiscObjects.Room;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class RoomDatabase {

    private static final List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public RoomDatabase() {
        List<Time> availableTimes = new ArrayList<>();
        Time availableTime = new Time(59400000);
        availableTimes.add(availableTime);
        availableTime = new Time(66600000);
        availableTimes.add(availableTime);

        Room room = new Room("room 1", availableTimes, new ArrayList<Event>());

        addRoom(room);

        availableTime = new Time(45000000);
        availableTimes.add(availableTime);
        availableTime = new Time(52200000);
        availableTimes.add(availableTime);
        room = new Room("room 2", availableTimes, new ArrayList<Event>());

        addRoom(room);

        availableTime = new Time(30600000);
        availableTimes.add(availableTime);
        availableTime = new Time(37800000);
        availableTimes.add(availableTime);
        room = new Room("room 3", availableTimes, new ArrayList<Event>());

        addRoom(room);

    }
}
