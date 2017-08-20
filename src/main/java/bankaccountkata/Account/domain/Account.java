package bankaccountkata.Account.domain;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class Account {
    private AccountId accountId;

    public Account(final AccountId accountId) {

        this.accountId = accountId;
    }

    public AccountId accountId() {
        return accountId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Account account = (Account) o;

        return accountId != null ? accountId.equals(account.accountId) : account.accountId == null;
    }

    @Override
    public int hashCode() {
        return accountId != null ? accountId.hashCode() : 0;
    }
}
