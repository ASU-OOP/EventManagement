package Models.Users;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Admin extends User {

    String role;
    LocalTime shiftStartTime;
    LocalTime shiftEndTime;

    public String getRole() {
        return role;
    }

    public LocalTime getShiftStartTime() {
        return shiftStartTime;
    }

    public LocalTime getShiftEndTime() {
        return shiftEndTime;
    }

    public String getWorkingHours() {
        return shiftStartTime.toString() + "-" + shiftEndTime.toString();
    }

    public void setRole(String role) {
        this.role = role;
    }

    void setShiftStartTime(LocalTime shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    void setShiftEndTime(LocalTime shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

     public Admin(String username, String password, LocalDate dateOfBirth, String role, LocalTime shiftStartTime, LocalTime shiftEndTime) {
        super(username, password, dateOfBirth);

        setUserType(UserType.ADMIN);

        setRole(role);
        setShiftStartTime(shiftStartTime);
        setShiftEndTime(shiftEndTime);
    }
}
