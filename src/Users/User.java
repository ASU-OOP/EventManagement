package Users;

import java.util.Date;

// This class is the template for all other User classes
public abstract class User implements Comparable<User>{
    public String username = "";
    public String password = "";
    Date dateOfBirth;
    boolean active = false;
    UserType userType;

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    Date getDateOfBirth() {
        return dateOfBirth;
    }

    Boolean getActiveStatus() {
        return active;
    }

    UserType getUserType() {
        return userType;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    void setActiveStatus(Boolean active) {
        this.active = active;
    }

    void setUserType(UserType userType) {
        this.userType = userType;
    }

    boolean deactivateUser() {
        setPassword(null);
        setActiveStatus(false);
        return true;
    }

    protected User(String username, String password, Date dateOfBirth) {
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
