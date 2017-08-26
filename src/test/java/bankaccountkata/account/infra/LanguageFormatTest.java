package bankaccountkata.account.infra;

import bankaccountkata.account.domain.LanguageFormat;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertThat;

/**
 * Created by medaghrialaouiyoussef on 20/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LanguageFormatTest {

    @Test
    public void should_format_key_to_english() {
        LanguageFormat languageFormat = LanguageFormat.ENGLISH;
        String test = languageFormat.formatKey("test");
        assertThat(test, Is.is("This is a test."));
    }

    @Test
    public void should_format_key_to_french() {
        LanguageFormat languageFormat = LanguageFormat.FRENCH;
        String test = languageFormat.formatKey("test");
        assertThat(test, Is.is("Ceci est un test."));
    }

    @Test
    public void should_format_date_to_english() {
        LanguageFormat languageFormat = LanguageFormat.ENGLISH;
        String test = languageFormat.formatDate(LocalDate.of(2017, 01, 10));
        assertThat(test, Is.is("01/10/2017"));
    }

    @Test
    public void should_format_date_to_french() {
        LanguageFormat languageFormat = LanguageFormat.FRENCH;
        String test = languageFormat.formatDate(LocalDate.of(2017, 01, 10));
        assertThat(test, Is.is("10/01/2017"));
    }
}