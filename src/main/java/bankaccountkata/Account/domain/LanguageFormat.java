package bankaccountkata.Account.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by ymedaghri on 18/08/2017.
 */
public enum LanguageFormat {
    FRENCH {
        private ResourceBundle bundle = ResourceBundle.getBundle("french");


        @Override
        public ResourceBundle getBundle() {
            return bundle;
        }


        @Override
        public String formatAmount(double amount) {
            return String.format(Locale.FRENCH, "%.2f", amount);
        }

        @Override
        protected String getDateFormat() {
            return "dd/MM/yyyy";
        }

    },
    ENGLISH {
        private ResourceBundle bundle = ResourceBundle.getBundle("english");


        @Override
        public ResourceBundle getBundle() {
            return bundle;
        }


        @Override
        public String formatAmount(double amount) {
            return String.format(Locale.ENGLISH, "%.2f", amount);
        }

        @Override
        protected String getDateFormat() {
            return "MM/dd/yyyy";
        }

    };


    public abstract ResourceBundle getBundle();


    public String formatKey(String key) {
        return getBundle().getString(key);
    }


    public abstract String formatAmount(double amount);

    public String formatDate(final LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateFormat());
        return date.format(formatter);
    }

    protected abstract String getDateFormat();


}
