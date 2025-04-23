package MiscObjects;

import Users.Attendee;
import Users.Organizer;

import java.util.Date;
import java.util.List;

public class Event {
    String name;
    Date date;
    List<Attendee> attendees;
    Organizer organizer;
    Room room;
    Category category;
}
