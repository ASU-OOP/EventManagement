package Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Attendee extends WalletUser {
    String address = "";
    Gender gender;
    List<String> interests = new ArrayList<>();

    public String getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

     Attendee(String username, String password, Date dateOfBirth, Double balance, String address, Gender gender, List<String> interests) {
        super(username, password, dateOfBirth, balance);

        setUserType(UserType.ATTENDEE);

        setAddress(address);
        setGender(gender);
        setInterests(interests);
    }
}
