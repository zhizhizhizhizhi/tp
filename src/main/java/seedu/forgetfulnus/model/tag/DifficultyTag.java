package seedu.forgetfulnus.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the Glossary.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDifficultyTag(String)}
 */
public class DifficultyTag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should EASY, MEDIUM or HARD only";
    public static final String EASY_TAG = "EASY";
    public static final String MEDIUM_TAG = "MEDIUM";
    public static final String HARD_TAG = "HARD";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     * Default difficulty tag is MEDIUM
     */
    public DifficultyTag() {
        this.tagName = MEDIUM_TAG;
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public DifficultyTag(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
        checkArgument(isValidDifficultyTag(tagName), MESSAGE_CONSTRAINTS);
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
