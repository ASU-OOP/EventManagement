package Utils;

import Models.MiscObjects.Event;
import Models.MiscObjects.Room;
import Models.Users.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PrintUtils {

    public String getEvents(List<Event> events){
        StringBuilder eventsString = new StringBuilder();

        for (Event event : events) {
            eventsString.append(event.toString()).append(", ");
        }

        return eventsString.toString();
    }

    public String getAvailableHours(List<Time> hours){
        StringBuilder hoursString = new StringBuilder();

        for (Time hour : hours) {
            hoursString.append(hour.toString()).append(", ");
        }

        return hoursString.toString();
    }

    public String getEventAttendees(List<Attendee> attendees){
        StringBuilder attendeesString = new StringBuilder();
        for (Attendee attendee : attendees) {
            attendeesString.append(attendee.username).append(", ");
        }

        return attendeesString.toString();
    }
}
