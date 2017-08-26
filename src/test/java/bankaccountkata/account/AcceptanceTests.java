package bankaccountkata.account;

import bankaccountkata.account.domain.*;
import bankaccountkata.account.infra.Console;
import bankaccountkata.account.infra.InMemoryAccountRepository;
import bankaccountkata.account.infra.LanguageFormatter;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AcceptanceTests {

    public static final Long ACCOUNT_ID = 1234567l;
    @Mock
    private OperationDateGenerator dateGenerator;
    private LocalDate January_02_2017 = LocalDate.of(2017, 01, 02);

    @Before
    public void setUp() {
        Mockito.when(dateGenerator.now()).thenReturn(January_02_2017);
    }


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


    @Test
    public void should_print_history_when_asked_for_operations_from_a_console() {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        Account account = new Account(AccountId.from(ACCOUNT_ID), dateGenerator);
        accountRepository.save(account);

        account.deposit(1000.00);
        account.withdraw(252.49);
        accountRepository.update(account);

        AccountHistoryPrinter console = Mockito.mock(Console.class);
        OperationFormater formater = new LanguageFormatter(LanguageFormat.ENGLISH);
        account.printHistoryTo(console, formater);

        InOrder inOrderVerificator = Mockito.inOrder(console);
        inOrderVerificator.verify(console).printLine("DEPOSIT, 01/02/2017, 1000.00, 1000.00");
        inOrderVerificator.verify(console).printLine("WITHDRAWAL, 01/02/2017, 252.49, 747.51");
    }

}
