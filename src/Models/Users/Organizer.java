package Models.Users;

import java.time.LocalDate;
import java.util.Date;

public class Organizer extends WalletUser {

     public Organizer(String username, String password, LocalDate dateOfBirth, Double balance) {
        super(username, password, dateOfBirth, balance);

        setUserType(UserType.ORGANIZER);
     }
}
