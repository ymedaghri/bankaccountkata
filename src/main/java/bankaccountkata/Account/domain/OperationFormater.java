package bankaccountkata.Account.domain;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public interface OperationFormater {
    String format(Operation operation, Double accountBalance);
}
