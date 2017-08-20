import bankaccountkata.Account.domain.Account;
import bankaccountkata.Account.domain.AccountId;
import bankaccountkata.Account.domain.LanguageFormat;
import bankaccountkata.Account.domain.OperationFormater;
import bankaccountkata.Account.infra.Console;
import bankaccountkata.Account.infra.LanguageFormatter;

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
