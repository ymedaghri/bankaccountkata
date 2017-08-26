package bankaccountkata.account.domain;

import java.util.List;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public interface AccountRepository {

    Account findAccountByID(AccountId accountId);

    List<Account> findAll();

    Account save(Account account);

    Account update(Account account);
}
