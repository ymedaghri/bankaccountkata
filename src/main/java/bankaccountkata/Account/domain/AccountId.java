package bankaccountkata.account.domain;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class AccountId {
    private Long accountId;

    private AccountId(){}

    private AccountId(final Long accountId) {

        this.accountId = accountId;
    }

    public static AccountId from(final Long accountId) {
        return new AccountId(accountId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AccountId accountId1 = (AccountId) o;

        return accountId != null ? accountId.equals(accountId1.accountId) : accountId1.accountId == null;
    }

    @Override
    public int hashCode() {
        return accountId != null ? accountId.hashCode() : 0;
    }
}
