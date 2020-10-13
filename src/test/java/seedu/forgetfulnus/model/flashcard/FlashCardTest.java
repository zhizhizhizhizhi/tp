package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.MONDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TABLE;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class FlashCardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        FlashCard flashCard = new FlashCardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashCard.getTags().remove(0));
    }

    @Test
    public void isSameFlashCard() {
        // same object -> returns true
        assertTrue(MONDAY.isSameFlashCard(MONDAY));

        // null -> returns false
        assertFalse(MONDAY.isSameFlashCard(null));

        // different phone and email -> returns false
        FlashCard editedAlice = new FlashCardBuilder(MONDAY)
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
                .build();
        assertFalse(MONDAY.isSameFlashCard(editedAlice));

        // different name -> returns false
        editedAlice = new FlashCardBuilder(MONDAY).withGermanPhrase(VALID_GERMAN_PHRASE_TABLE).build();
        assertFalse(MONDAY.isSameFlashCard(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new FlashCardBuilder(MONDAY)
                .withTags(VALID_TAG_CHAPTER_ONE).build();
        assertTrue(MONDAY.isSameFlashCard(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new FlashCardBuilder(MONDAY).withTags(VALID_TAG_CHAPTER_ONE).build();
        assertTrue(MONDAY.isSameFlashCard(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        FlashCard aliceCopy = new FlashCardBuilder(MONDAY).build();
        assertTrue(MONDAY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(MONDAY.equals(MONDAY));

        // null -> returns false
        assertFalse(MONDAY.equals(null));

        // different type -> returns false
        assertFalse(MONDAY.equals(5));

        // different flashcard -> returns false
        assertFalse(MONDAY.equals(TABLE));

        // different name -> returns false
        FlashCard editedAlice = new FlashCardBuilder(MONDAY).withGermanPhrase(VALID_GERMAN_PHRASE_TABLE).build();
        assertFalse(MONDAY.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new FlashCardBuilder(MONDAY).withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE).build();
        assertFalse(MONDAY.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FlashCardBuilder(MONDAY).withTags(VALID_TAG_CHAPTER_ONE).build();
        assertFalse(MONDAY.equals(editedAlice));
    }
}
