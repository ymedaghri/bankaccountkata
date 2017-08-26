package bankaccountkata.account.infra;

import bankaccountkata.account.domain.Account;
import bankaccountkata.account.domain.AccountId;
import bankaccountkata.account.domain.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
@Component
public class InMemoryAccountRepository implements AccountRepository {
    Set<Account> accounts = new HashSet<>();


    @Override
    public Account findAccountByID(AccountId accountId) {
        return accounts.stream().filter(account -> account.getAccountId().equals(accountId)).findFirst().orElse(null);
    }

    @Override
    public List<Account> findAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.addAll(this.accounts);
        return accounts;
    }


    @Override
    public Account save(Account account) {
        if (accounts.contains(account)) accounts.remove(account);
        accounts.add(account);
        return account;
    }

    @Override
    public Account update(final Account account) {
        Account storedAccount = findAccountByID(account.getAccountId());
        storedAccount.setOperations(account.getOperations());
        return storedAccount;
    }


}
