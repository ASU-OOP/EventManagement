package Models.Users;

import java.util.Date;

public class Attendee extends WalletUser {
    String address = "";
    Gender gender;
    String interests = "";

    public String getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public String getInterests() {
        return interests;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Attendee(String username, String password, Date dateOfBirth, Double balance, String address, Gender gender, String interests) {
        super(username, password, dateOfBirth, balance);

        setUserType(UserType.ATTENDEE);

        setAddress(address);
        setGender(gender);
        setInterests(interests);
    }
}
