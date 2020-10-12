package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GermanPhraseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GermanPhrase(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new GermanPhrase(invalidName));
    }

    //TODO change constraints to english alphabets only
    //change examples
    @Test
    public void isValidGermanPhrase() {
        // null name
        assertThrows(NullPointerException.class, () -> GermanPhrase.isValidGermanPhrase(null));

        // invalid German phrase
        assertFalse(GermanPhrase.isValidGermanPhrase("")); // empty string
        assertFalse(GermanPhrase.isValidGermanPhrase(" ")); // spaces only
        assertFalse(GermanPhrase.isValidGermanPhrase("^")); // only non-alphanumeric characters
        assertFalse(GermanPhrase.isValidGermanPhrase("peter*")); // contains non-alphanumeric characters

        // valid German phrase
        assertTrue(GermanPhrase.isValidGermanPhrase("peter jack")); // alphabets only
        assertTrue(GermanPhrase.isValidGermanPhrase("12345")); // numbers only
        assertTrue(GermanPhrase.isValidGermanPhrase("peter the 2nd")); // alphanumeric characters
        assertTrue(GermanPhrase.isValidGermanPhrase("Capital Tan")); // with capital letters
        assertTrue(GermanPhrase.isValidGermanPhrase("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
