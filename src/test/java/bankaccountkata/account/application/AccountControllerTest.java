package bankaccountkata.account.application;

import bankaccountkata.account.BankaccountApplication;
import bankaccountkata.account.BankaccountApplicationTests;
import bankaccountkata.account.domain.Account;
import bankaccountkata.account.domain.AccountId;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;

/**
 * Created by medaghrialaouiyoussef on 25/08/2017.
 */
public class AccountControllerTest extends BankaccountApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void create_accounts_then_list_them_all() {
        ResponseEntity<AccountJson> response_1 = restTemplate.postForEntity("/account", new AccountJson(123456l), AccountJson.class);
        assertThat(response_1.getStatusCode(), Is.is(HttpStatus.OK));
        assertThat(response_1.getBody().getAccountId(), Is.is(123456l));

        ResponseEntity<AccountJson> response_2 = restTemplate.postForEntity("/account", new AccountJson(345678l), AccountJson.class);
        assertThat(response_2.getStatusCode(), Is.is(HttpStatus.OK));
        assertThat(response_2.getBody().getAccountId(), Is.is(345678l));

        ResponseEntity<AccountsJson> response = allAccounts();
        assertThat(response.getStatusCode(), Is.is(HttpStatus.OK));
        List<AccountJson> accounts = response.getBody().getAccounts();
        Assertions.assertThat(accounts).containsExactlyInAnyOrder(new AccountJson(123456l), new AccountJson(345678l));
    }

    @Test
    public void find_by_id_non_existing_account() {
        ResponseEntity<AccountJson> response = restTemplate.getForEntity(format("/account/%s", 123456l), AccountJson.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void create_an_account_and_find_it_by_id() {
        AccountJson createdAccount_1 = restTemplate.postForEntity("/account", new AccountJson(785647l), AccountJson.class).getBody();
        assertThat(createdAccount_1.getAccountId(), Is.is(785647l));

        ResponseEntity<AccountJson> response = restTemplate.getForEntity(format("/account/%s", 785647l), AccountJson.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getAccountId()).isEqualTo(785647l);
    }

    private ResponseEntity<AccountsJson> allAccounts() {
        return restTemplate.getForEntity("/accounts", AccountsJson.class);
    }


}
