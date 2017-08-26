package bankaccountkata.account.domain;

import bankaccountkata.account.infra.InMemoryAccountRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    public static final Long ACCOUNT_ID = 123l;
    public static final LocalDate January_01_2017 = LocalDate.of(2017, 1, 1);

    @Mock
    private OperationDateGenerator dateGenerator;

    @Before
    public void setUp() {
        Mockito.when(dateGenerator.now()).thenReturn(January_01_2017);
    }

    @Test
    public void should_make_a_deposit_to_an_account() {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        Account account = new Account(AccountId.from(ACCOUNT_ID), dateGenerator);
        accountRepository.save(account);

        account.deposit(1000.00);
        Account updatedAccount = accountRepository.update(account);

        List<Operation> operations = updatedAccount.getOperations();
        assertThat(operations.size(), Is.is(1));
        assertThat(operations.get(0).type(), Is.is(Operation.OperationType.DEPOSIT));
        assertThat(operations.get(0).amount(), Is.is(1000.00));
        assertThat(operations.get(0).date(), Is.is(January_01_2017));
    }

    @Test
    public void should_make_a_withdrawal_from_an_account() {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        Account account = new Account(AccountId.from(ACCOUNT_ID), dateGenerator);
        accountRepository.save(account);

        account.deposit(1000.00);
        account.withdraw(500.00);
        Account updatedAccount = accountRepository.update(account);

        List<Operation> operations = updatedAccount.getOperations();
        assertThat(operations.size(), Is.is(2));
        assertThat(operations.get(1).type(), Is.is(Operation.OperationType.WITHDRAWAL));
        assertThat(operations.get(1).amount(), Is.is(500.00));
        assertThat(operations.get(0).date(), Is.is(January_01_2017));
    }

}