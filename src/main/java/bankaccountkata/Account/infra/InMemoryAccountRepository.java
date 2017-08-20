package bankaccountkata.Account.infra;

import bankaccountkata.Account.domain.Account;
import bankaccountkata.Account.domain.AccountId;
import bankaccountkata.Account.domain.AccountRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class InMemoryAccountRepository implements AccountRepository {
    Set<Account> accounts = new HashSet<>();


    @Override
    public Account findAccountByID(AccountId accountId)
    {
        return accounts.stream().filter(account -> account.accountId().equals(accountId)).findFirst().orElse(null);
    }


    @Override
    public Account save(Account account)
    {
        if(accounts.contains(account)) accounts.remove(account);
        accounts.add(account);
        return account;
    }

    @Override
    public Account update(final Account account) {
        Account storedAccount = findAccountByID(account.accountId());
        storedAccount.setOperations(account.getOperations());
        return storedAccount;
    }

}
