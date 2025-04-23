package Users;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admin extends User {

    String role;
    Time workingHours;

    public String getRole() {
        return role;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
    }

     Admin(String username, String password, Date dateOfBirth, String role, Time workingHours) {
        super(username, password, dateOfBirth);

         setUserType(UserType.ADMIN);


         setRole(role);
        setWorkingHours(workingHours);
    }
}
