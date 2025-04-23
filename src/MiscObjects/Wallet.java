package MiscObjects;

import Users.WalletUser;

public class Wallet {
    WalletUser owner;
    Double balance = 0.0;

    public WalletUser getOwner() {
        return owner;
    }

    public Double getBalance() {
        return balance;
    }

    public void setOwner(WalletUser owner) {
        this.owner = owner;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Wallet(WalletUser owner, Double balance) {
        setOwner(owner);
        setBalance(balance);
    }
}
