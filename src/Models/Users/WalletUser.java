package Models.Users;

import Models.MiscObjects.Wallet;

import java.time.LocalDate;
import java.util.Date;

public class WalletUser extends User {

    public Wallet wallet;

    public Double getBalance() {
        return wallet.getBalance();
    }

    public void setBalance(Double balance) {
        this.wallet.setBalance(balance);
    }

    protected WalletUser(String username, String password, LocalDate dateOfBirth, Double balance) {
        super(username, password, dateOfBirth);

        this.wallet = new Wallet(this, balance);
    }
}
