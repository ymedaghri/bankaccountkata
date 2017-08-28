package bankaccountkata.account.application;

import java.util.List;

/**
 * Created by medaghrialaouiyoussef on 26/08/2017.
 */
public class AccountsJson {

    List<AccountJson> accounts;

    public AccountsJson(){}

    public AccountsJson(List<AccountJson> accounts){
        this.accounts=accounts;
    }

    public List<AccountJson> getAccounts() {
        return accounts;
    }
}
