package seedu.forgetfulnus.model.tag;

import seedu.forgetfulnus.model.flashcard.EnglishPhrase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DifficultyTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    //TODO
    //change default tag to MEDIUM
    @Test
    public void isValidDifficultyTagName() {
        // null difficulty tag
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

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

