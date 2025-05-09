package StaticResources;

import Models.Users.Admin;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class AdminsDatabase {
    private static final List<Admin> admins = new ArrayList<>();

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
        LocalDate dateofBirth = LocalDate.ofInstant(new GregorianCalendar(103, Calendar.SEPTEMBER, 10).getTime().toInstant(), ZoneId.systemDefault());

        // Time in ms
        LocalTime shiftStart = LocalTime.of(8, 0);
        LocalTime shiftEnd = LocalTime.of(16, 0);

        Admin admin = new Admin("ramii", "password", dateofBirth, "Manager", shiftStart, shiftEnd);

        addAdmin(admin);

        dateofBirth = LocalDate.ofInstant(new GregorianCalendar(80, Calendar.DECEMBER, 24).getTime().toInstant(), ZoneId.systemDefault());

        shiftStart = LocalTime.of(17, 0);
        shiftEnd = LocalTime.of(0, 0);

        admin = new Admin("medhat", "notpassword", dateofBirth, "CEO", shiftStart, shiftEnd);

        addAdmin(admin);

        dateofBirth = LocalDate.ofInstant(new GregorianCalendar(1000, Calendar.JANUARY, 15).getTime().toInstant(), ZoneId.systemDefault());
        shiftStart = LocalTime.of(0, 0);
        shiftEnd = LocalTime.of(8, 0);

        admin = new Admin("ahmed", "notnotpassword", dateofBirth, "Middle-Management", shiftStart, shiftEnd);

        addAdmin(admin);
    }
}
