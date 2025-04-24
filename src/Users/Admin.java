package Users;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Admin extends User {

    String role;
    List<Time> workingHours;

    public String getRole() {
        return role;
    }

    public List<Time> getWorkingHours() {
        return workingHours;
    }

    public void setRole(String role) {
        this.role = role;
    }

    void setWorkingHours(List<Time> workingHours) {
        this.workingHours = workingHours;
    }

    public void AddWorkingHour(Time workingHour) {
        workingHours.add(workingHour);
    }

    public void RemoveWorkingHour(Time workingHour) {
        workingHours.remove(workingHour);
    }

     public Admin(String username, String password, Date dateOfBirth, String role, List<Time> workingHours) {
        super(username, password, dateOfBirth);

        setUserType(UserType.ADMIN);

        setRole(role);
        setWorkingHours(workingHours);
    }
}
