package bankaccountkata.Account;

import bankaccountkata.Account.domain.Account;
import bankaccountkata.Account.domain.AccountId;
import bankaccountkata.Account.domain.AccountRepository;
import bankaccountkata.Account.infra.InMemoryAccountRepository;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
@RunWith(JUnit4.class)
public class AcceptanceTests {

    public static final Long ACCOUNT_ID = 1234567l;

    @Test
    public void should_create_an_account_if_it_does_not_exist() {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        Account account = accountRepository.findAccountByID(AccountId.from(ACCOUNT_ID));
        assertNull(account);
        account = new Account(AccountId.from(ACCOUNT_ID));
        accountRepository.save(account);
        Account accountFound = accountRepository.findAccountByID(AccountId.from(ACCOUNT_ID));
        assertThat(accountFound, Is.is(account));
    }
}
