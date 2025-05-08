package StaticResources;

import Models.Users.Organizer;

import java.util.*;

public class OrganizersDatabase {

    public static final List<Organizer> organizers = new ArrayList<>();

    public List<Organizer> getOrganizers() {
        return organizers;
    }

    public void addOrganizer(Organizer organizer) {
        organizers.add(organizer);
    }

    public void removeOrganizer(Organizer organizer) {
        organizers.remove(organizer);
    }

    public OrganizersDatabase() {
        Date dateofBirth = new GregorianCalendar(103, Calendar.SEPTEMBER, 10).getTime();

        Organizer organizer = new Organizer("notramii", "passwordnot", dateofBirth, 1000.0);

        addOrganizer(organizer);

        dateofBirth = new GregorianCalendar(80, Calendar.DECEMBER, 24).getTime();
        organizer = new Organizer("notmedhat", "notpasswordnot", dateofBirth, 5000.0);

        addOrganizer(organizer);
    }
}
