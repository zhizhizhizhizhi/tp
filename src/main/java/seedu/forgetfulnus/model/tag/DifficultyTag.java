package seedu.forgetfulnus.model.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.forgetfulnus.commons.util.AppUtil.checkArgument;

public class DifficultyTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should EASY, MEDIUM or HARD only";
    public static final String EASY_TAG = "EASY";
    public static final String MEDIUM_TAG = "MEDIUM";
    public static final String HARD_TAG = "HARD";


    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public DifficultyTag(String tagName) {
        super(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDifficultyTag(String testInput) {
        String test = testInput.replaceAll("\\s+", "");
        return test.equalsIgnoreCase(EASY_TAG) || test.equalsIgnoreCase(MEDIUM_TAG) || test.equalsIgnoreCase(HARD_TAG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DifficultyTag // instanceof handles nulls
                && tagName.equals(((DifficultyTag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }
}
