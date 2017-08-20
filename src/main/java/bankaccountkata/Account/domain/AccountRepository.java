package bankaccountkata.Account.domain;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public interface AccountRepository {

    Account findAccountByID(AccountId accountId);

    Account save(Account account);

    Account update(Account account);
}
