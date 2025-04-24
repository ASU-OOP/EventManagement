package StaticResources;

import Users.Admin;

import java.sql.Time;
import java.util.*;

public class AdminsDatabase {
    private static final List<Admin> admins = new ArrayList<>();
    private static final List<Time> workingHours = new ArrayList<>();

    private void addWorkingHour(Time workingHour) {
        workingHours.add(workingHour);
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    public AdminsDatabase() {
        Date dateofBirth = new GregorianCalendar(103, Calendar.SEPTEMBER, 10).getTime();

        // Time in ms
        Time workingHour = new Time(59400000);
        addWorkingHour(workingHour);
        workingHour = new Time(66600000);
        addWorkingHour(workingHour);

        Admin admin = new Admin("ramii", "password", dateofBirth, "Manager", workingHours);

        addAdmin(admin);

        dateofBirth = new GregorianCalendar(80, Calendar.DECEMBER, 24).getTime();
        workingHour = new Time(45000000);
        addWorkingHour(workingHour);
        workingHour = new Time(52200000);
        addWorkingHour(workingHour);

        admin = new Admin("medhat", "notpassword", dateofBirth, "CEO", workingHours);

        addAdmin(admin);

        dateofBirth = new GregorianCalendar(1000, Calendar.JANUARY, 15).getTime();
        workingHour = new Time(30600000);
        addWorkingHour(workingHour);
        workingHour = new Time(37800000);
        addWorkingHour(workingHour);

        admin = new Admin("ahmed", "notnotpassword", dateofBirth, "Middle-Management", workingHours);

        addAdmin(admin);
    }
}
