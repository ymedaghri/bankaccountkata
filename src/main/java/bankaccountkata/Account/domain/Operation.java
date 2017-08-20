package bankaccountkata.Account.domain;

import java.time.LocalDate;

public interface Operation {
    LocalDate date();

    enum OperationType {
        DEPOSIT {
            @Override
            public String format(LanguageFormat languageFormat) {
                return languageFormat.formatKey("DEPOSIT");
            }
        }, WITHDRAWAL {
            @Override
            public String format(LanguageFormat languageFormat) {
                return languageFormat.formatKey("WITHDRAWAL");
            }
        };


        public abstract String format(LanguageFormat languageFormat);
    }

    Double amount();

    Integer amountDirection();

    OperationType type();
}


