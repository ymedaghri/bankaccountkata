package bankaccountkata.Account.infra;

import bankaccountkata.Account.domain.LanguageFormat;
import bankaccountkata.Account.domain.Operation;
import bankaccountkata.Account.domain.OperationFormater;


/**
 * Created by ymedaghri on 18/08/2017.
 */
public class LanguageFormatter implements OperationFormater {
    private LanguageFormat languageFormat;

    public LanguageFormatter(LanguageFormat languageFormat) {
        this.languageFormat = languageFormat;
    }

    public String format(Operation operation, Double accountBalance) {
        String separator = languageFormat.formatKey("SEPARATOR");
        return new StringBuilder()
                .append(operation.type().format(languageFormat))
                .append(separator)
                .append(languageFormat.formatDate(operation.date()))
                .append(separator)
                .append(languageFormat.formatAmount(operation.amount()))
                .append(separator)
                .append(languageFormat.formatAmount(accountBalance))
                .toString();
    }

}
