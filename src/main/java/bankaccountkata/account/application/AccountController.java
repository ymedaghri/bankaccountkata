package bankaccountkata.account.application;

import bankaccountkata.account.domain.Account;
import bankaccountkata.account.domain.AccountId;
import bankaccountkata.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by medaghrialaouiyoussef on 25/08/2017.
 */
@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(path = "/accounts")
    public AccountsJson getAccounts() {
        return new AccountsJson(accountRepository.findAll().stream().map(
                account -> AccountJson.fromModel(account)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/account/{accountId}", method = GET)
    @ResponseBody
    public AccountJson get(@PathVariable String accountId) {
        Account accountByID = accountRepository.findAccountByID(AccountId.from(Long.valueOf(accountId)));
        if (accountByID == null) {
            throw new AccountNotFound();
        }
        return AccountJson.fromModel(accountByID);
    }

    @PostMapping(path = "/account")
    public AccountJson createAccount(@RequestBody AccountJson newAccount) {
        return AccountJson.fromModel(accountRepository.save(new Account(AccountId.from(newAccount.getAccountId()))));
    }

    /*
    Console console = new Console();

        Account account = new Account(AccountId.from(192883l));
        account.deposit(1000.00);
        account.withdraw(250.00);
        account.deposit(300.00);
        account.deposit(300.00);
        account.withdraw(100.00);

        OperationFormater formater = new LanguageFormatter(LanguageFormat.ENGLISH);
        account.printHistoryTo(console, formater);
     */
}
