package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.DESC_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.DESC_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.EditCommand.EditFlashCardDescriptor;
import seedu.forgetfulnus.testutil.EditFlashCardDescriptorBuilder;

public class EditFlashCardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashCardDescriptor descriptorWithSameValues = new EditFlashCardDescriptor(DESC_FORGETFULNESS);
        assertTrue(DESC_FORGETFULNESS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FORGETFULNESS.equals(DESC_FORGETFULNESS));

        // null -> returns false
        assertFalse(DESC_FORGETFULNESS.equals(null));

        // different types -> returns false
        assertFalse(DESC_FORGETFULNESS.equals(5));

        // different values -> returns false
        assertFalse(DESC_FORGETFULNESS.equals(DESC_TABLE));

        // different name -> returns false
        EditFlashCardDescriptor editedAmy = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS)
                .withGermanPhrase(VALID_GERMAN_PHRASE_TABLE).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS).withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS).withTags(VALID_TAG_CHAPTER_ONE).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedAmy));
    }
}
