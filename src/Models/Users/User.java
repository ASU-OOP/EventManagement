package Models.Users;

import java.time.LocalDate;
import java.util.Date;

// This class is the template for all other User classes
public abstract class User implements Comparable<User>{
    public String username = "";
    public String password = "";
    LocalDate dateOfBirth;
    boolean active = false;
    UserType userType;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getActiveStatus() {
        return active;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setActiveStatus(Boolean active) {
        this.active = active;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    boolean deactivateUser() {
        setPassword(null);
        setActiveStatus(false);
        return true;
    }

    protected User(String username, String password, LocalDate dateOfBirth) {
        setUsername(username);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
        setActiveStatus(true);
    }

    @Override
    public int compareTo(User user) {
        if (getUsername() == null || user.getUsername() == null) {
            return 0;
        }
        return getUsername().compareTo(user.getUsername());
    }
}
