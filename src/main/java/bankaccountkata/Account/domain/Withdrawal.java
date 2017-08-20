package bankaccountkata.Account.domain;


import static bankaccountkata.Account.domain.Operation.OperationType.WITHDRAWAL;

/**
 * Created by ymedaghri on 18/08/2017.
 */
public class Withdrawal implements Operation {
    private Double amount;


    public Withdrawal(Double amount) {
        this.amount = amount;
    }


    @Override
    public Double amount() {
        return amount;
    }


    @Override
    public OperationType type() {
        return WITHDRAWAL;
    }
}
