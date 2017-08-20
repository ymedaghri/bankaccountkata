package bankaccountkata.Account.domain;


import java.time.LocalDate;

import static bankaccountkata.Account.domain.Operation.OperationType.WITHDRAWAL;

/**
 * Created by ymedaghri on 18/08/2017.
 */
public class Withdrawal implements Operation {
    private Double amount;
    private LocalDate date;


    public Withdrawal(Double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }


    @Override
    public LocalDate date() {
        return date;
    }

    @Override
    public Double amount() {
        return amount;
    }

    @Override
    public Integer amountDirection() {
        return -1;
    }


    @Override
    public OperationType type() {
        return WITHDRAWAL;
    }
}
