package bankaccountkata.Account.domain;

public interface Operation {
    enum OperationType {
        DEPOSIT, WITHDRAWAL;
    }

    Double amount();

    OperationType type();
}


