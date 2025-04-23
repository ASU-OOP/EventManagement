package Users;

import MiscObjects.Wallet;

import java.util.Date;

public class WalletUser extends User {

    public Wallet wallet;

    public Double getBalance() {
        return wallet.getBalance();
    }

    public void setBalance(Double balance) {
        this.wallet.setBalance(balance);
    }

    protected WalletUser(String username, String password, Date dateOfBirth, Double balance) {
        super(username, password, dateOfBirth);

        Wallet wallet = new Wallet(this, balance);
    }
}
