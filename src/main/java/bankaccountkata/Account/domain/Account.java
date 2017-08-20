package bankaccountkata.Account.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class Account {

    private OperationDateGenerator operationDateGenerator;
    private AccountId accountId;

    List<Operation> operations = new ArrayList<>();

    public Account(final AccountId accountId) {

        this.accountId = accountId;
        operationDateGenerator=new OperationDateGenerator() {
            @Override
            public LocalDate now() {
                return LocalDate.now();
            }
        };
    }

    public Account(final AccountId accountId, OperationDateGenerator operationDateGenerator) {

        this.accountId = accountId;
        this.operationDateGenerator=operationDateGenerator;
    }

    public AccountId accountId() {
        return accountId;
    }


    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(final List<Operation> operations) {
        this.operations = operations;
    }

    public void deposit(Double amount) {
        operations.add(new Deposit(amount, operationDateGenerator.now()));
    }


    public void withdraw(Double amount) {
        operations.add(new Withdrawal(amount, operationDateGenerator.now()));
    }

    public void printHistoryTo(AccountHistoryPrinter printer, OperationFormater formater)
    {
        DoubleAdder doubleAdder=new DoubleAdder();
        operations.forEach(operation -> {
            doubleAdder.add(operation.amount()*operation.amountDirection());
            printer.printLine(formater.format(operation, doubleAdder.doubleValue()));
        });
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Account account = (Account) o;

        return accountId != null ? accountId.equals(account.accountId) : account.accountId == null;
    }

    @Override
    public int hashCode() {
        return accountId != null ? accountId.hashCode() : 0;
    }
}
