package Users;

import java.util.Date;

public class Organizer extends WalletUser {

     Organizer(String username, String password, Date dateOfBirth, Double balance) {
        super(username, password, dateOfBirth, balance);

        setUserType(UserType.ORGANIZER);
     }
}
