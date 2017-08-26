package bankaccountkata.account.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
public class Account {

    private AccountId accountId;
    List<Operation> operations = new ArrayList<>();
    private OperationDateGenerator operationDateGenerator;

    public Account(final AccountId accountId) {

        this.accountId = accountId;
        operationDateGenerator = new OperationDateGenerator() {
            @Override
            public LocalDate now() {
                return LocalDate.now();
            }
        };
    }

    public Account(){

    }
    public Account(final AccountId accountId, OperationDateGenerator operationDateGenerator) {

        this.accountId = accountId;
        this.operationDateGenerator = operationDateGenerator;
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

    public void printHistoryTo(AccountHistoryPrinter printer, OperationFormater formater) {
        DoubleAdder doubleAdder = new DoubleAdder();
        operations.forEach(operation -> {
            doubleAdder.add(operation.amount() * operation.amountDirection());
            printer.printLine(formater.format(operation, doubleAdder.doubleValue()));
        });
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Account account = (Account) o;

        if (operationDateGenerator != null ? !operationDateGenerator.equals(account.operationDateGenerator) : account.operationDateGenerator != null)
            return false;
        if (accountId != null ? !accountId.equals(account.accountId) : account.accountId != null) return false;
        return operations != null ? operations.equals(account.operations) : account.operations == null;
    }

    @Override
    public int hashCode() {
        int result = operationDateGenerator != null ? operationDateGenerator.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (operations != null ? operations.hashCode() : 0);
        return result;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
