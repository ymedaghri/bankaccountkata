package bankaccountkata.account.application;

import bankaccountkata.account.domain.Account;

/**
 * Created by medaghrialaouiyoussef on 26/08/2017.
 */
public class AccountJson {

    private Long accountId;

    public AccountJson() {
    }

    public AccountJson(Long accountId) {
        this.accountId = accountId;
    }

    public static AccountJson fromModel(Account account) {
        return new AccountJson(account.getAccountId().getId());
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(final Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AccountJson that = (AccountJson) o;

        return accountId != null ? accountId.equals(that.accountId) : that.accountId == null;
    }

    @Override
    public int hashCode() {
        return accountId != null ? accountId.hashCode() : 0;
    }
}
