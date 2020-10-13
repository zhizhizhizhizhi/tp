package seedu.forgetfulnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.storage.JsonAdaptedFlashCard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TUESDAY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;

public class JsonAdaptedFlashCardTest {
    private static final String INVALID_GERMAN_PHRASE = "Deutschl@nd";
    private static final String INVALID_ENGLISH_PHRASE = "+Germany";
    private static final String INVALID_TAG = "#country";

    private static final String VALID_GERMAN_PHRASE = TUESDAY.getGermanPhrase().toString();
    private static final String VALID_ENGLISH_PHRASE = TUESDAY.getEnglishPhrase().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TUESDAY.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedFlashCard person = new JsonAdaptedFlashCard(TUESDAY);
        assertEquals(TUESDAY, person.toModelType());
    }

    @Test
    public void toModelType_invalidGermanPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard person =
                new JsonAdaptedFlashCard(INVALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE, VALID_TAGS);
        String expectedMessage = GermanPhrase.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGermanPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard person = new JsonAdaptedFlashCard(null, VALID_ENGLISH_PHRASE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GermanPhrase.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEnglishPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard person =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, INVALID_ENGLISH_PHRASE, VALID_TAGS);
        String expectedMessage = EnglishPhrase.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEnglishPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard person = new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EnglishPhrase.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashCard person =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
