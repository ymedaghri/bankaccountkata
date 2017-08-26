package bankaccountkata.account.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by medaghrialaouiyoussef on 26/08/2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such account")
public class AccountNotFound extends RuntimeException {
}
