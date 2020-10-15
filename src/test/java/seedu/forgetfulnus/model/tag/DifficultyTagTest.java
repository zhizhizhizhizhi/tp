package seedu.forgetfulnus.model.tag;

import seedu.forgetfulnus.model.flashcard.EnglishPhrase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DifficultyTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    // TODO
    //should create default difficulty tag as medium,
    @Test
    public void constructor_invalidDifficultyTagName_throwsIllegalArgumentException() {
        String invalidTagName = "EasyPeasy";
        assertThrows(IllegalArgumentException.class, () -> new DifficultyTag(invalidTagName));
    }

    @Test
    public void constructor_defaultTagName() {
        // Default tag name should be medium
        assertEquals(new DifficultyTag(), new DifficultyTag(DifficultyTag.MEDIUM_TAG));
    }

    //TODO
    //change default tag to MEDIUM
    @Test
    public void isValidDifficultyTagName() {
        // null difficulty tag
        assertThrows(NullPointerException.class, () -> DifficultyTag.isValidDifficultyTag(null));

        // valid difficulty tag
        assertTrue(DifficultyTag.isValidDifficultyTag("easy")); // Easy difficulty tag
        assertTrue(DifficultyTag.isValidDifficultyTag("medium")); // Medium difficulty tag
        assertTrue(DifficultyTag.isValidDifficultyTag("hard")); // Hard difficulty tag
        assertTrue(DifficultyTag.isValidDifficultyTag(" easy ")); // Easy difficulty tag with whitespace

        //invalid difficulty tags
        assertFalse(DifficultyTag.isValidDifficultyTag("easy peasy")); // invalid difficulty tag
        assertFalse(DifficultyTag.isValidDifficultyTag("2")); // invalid difficulty tag
    }
}

