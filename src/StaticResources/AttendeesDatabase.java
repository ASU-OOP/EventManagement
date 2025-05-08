package StaticResources;

import Models.Users.Attendee;
import Models.Users.Gender;

import java.util.*;

public class AttendeesDatabase {

    private static final List<Attendee> attendees = new ArrayList<>();

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void removeAttendee(Attendee attendee) {
        attendees.remove(attendee);
    }

    public AttendeesDatabase() {
        Date dateofBirth = new GregorianCalendar(2003, Calendar.SEPTEMBER, 10).getTime();
        String interests = "Dying, Romanticism, The fact that Pope Francis played undertale";
        Attendee attendee = new Attendee("ramiinot", "passnotword", dateofBirth, 100.0, "Maadi", Gender.MALE, interests);

        addAttendee(attendee);

        dateofBirth = new GregorianCalendar(1980, Calendar.DECEMBER, 24).getTime();
        interests = "Gaming, Bespoke Fashion (he's flashy), Nintendo not suing him for making Pokemon ROMs";
        attendee = new Attendee("medhatnot", "wordnotpass", dateofBirth, 500.0, "Helwan", Gender.MALE, interests);

        addAttendee(attendee);
    }
}
