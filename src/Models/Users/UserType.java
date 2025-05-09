package Models.Users;

public enum UserType {
    ADMIN("Admin"),
    ATTENDEE("Attendee"),
    ORGANIZER("Organizer");

    private String label;

    UserType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}