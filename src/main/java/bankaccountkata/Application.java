package bankaccountkata;

import bankaccountkata.account.domain.Account;
import bankaccountkata.account.domain.AccountId;
import bankaccountkata.account.domain.LanguageFormat;
import bankaccountkata.account.domain.OperationFormater;
import bankaccountkata.account.infra.Console;
import bankaccountkata.account.infra.LanguageFormatter;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class Application {
    public static void main(String[] args) {
        Console console = new Console();

        Account account = new Account(AccountId.from(192883l));
        account.deposit(1000.00);
        account.withdraw(250.00);
        account.deposit(300.00);
        account.deposit(300.00);
        account.withdraw(100.00);

        OperationFormater formater = new LanguageFormatter(LanguageFormat.ENGLISH);
        account.printHistoryTo(console, formater);
    }
}
