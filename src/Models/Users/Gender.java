package Models.Users;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),;

    private String label;

    Gender(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
