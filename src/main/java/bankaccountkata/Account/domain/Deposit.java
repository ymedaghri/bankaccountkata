package bankaccountkata.Account.domain;


import static bankaccountkata.Account.domain.Operation.OperationType.DEPOSIT;

/**
 * Created by ymedaghri on 18/08/2017.
 */
public class Deposit implements Operation {
    private Double amount;


    public Deposit(Double amount) {
        this.amount = amount;
    }


    @Override
    public Double amount() {
        return amount;
    }


    @Override
    public OperationType type() {
        return DEPOSIT;
    }
}
