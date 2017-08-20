package bankaccountkata.Account.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class Account {

    private AccountId accountId;

    List<Operation> operations = new ArrayList<>();

    public Account(final AccountId accountId) {

        this.accountId = accountId;
    }

    public AccountId accountId() {
        return accountId;
    }


    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(final List<Operation> operations) {
        this.operations = operations;
    }

    public void deposit(Double amount) {
        operations.add(new Deposit(amount));
    }


    public void withdraw(Double amount) {
        operations.add(new Withdrawal(amount));
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
