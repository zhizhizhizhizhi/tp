package seedu.forgetfulnus.model.flashcard;

import static seedu.forgetfulnus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Represents a Phrase in the Glossary.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FlashCard {

    // Identity fields
    private final GermanPhrase germanPhrase;
    private final EnglishPhrase englishPhrase;
    //TODO
    //change to private final
    private DifficultyTag difficultyTag;
    private final Set<Tag> tags = new HashSet<>();

    private boolean showingEnglish = true;

    /**
     * Every field must be present and not null.
     */
    public FlashCard(GermanPhrase germanPhrase, EnglishPhrase englishPhrase,
                     DifficultyTag difficultyTag, Set<Tag> tags) {
        requireAllNonNull(germanPhrase, englishPhrase, tags);
        this.germanPhrase = germanPhrase;
        this.englishPhrase = englishPhrase;
        this.difficultyTag = difficultyTag;
        this.tags.addAll(tags);
    }

    //TODO
    //remove this constructor
    /**
     * Every field must be present and not null.
     */
    public FlashCard(GermanPhrase germanPhrase, EnglishPhrase englishPhrase,
                      Set<Tag> tags) {
        requireAllNonNull(germanPhrase, englishPhrase, tags);
        this.germanPhrase = germanPhrase;
        this.englishPhrase = englishPhrase;
        this.tags.addAll(tags);
    }

    public GermanPhrase getGermanPhrase() {
        return germanPhrase;
    }

    public EnglishPhrase getEnglishPhrase() {
        return englishPhrase;
    }

    public DifficultyTag getDifficultyTag() {
        return difficultyTag;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both flashcards of the same German phrase have the same English phrase.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashCard(FlashCard otherFlashCard) {
        if (otherFlashCard == this) {
            return true;
        }

        return otherFlashCard != null
                && otherFlashCard.getGermanPhrase().equals(getGermanPhrase())
                && (otherFlashCard.getEnglishPhrase().equals(getEnglishPhrase()));
    }

    /**
     * Returns true if both flashcards have the same data fields.
     * This defines a stronger notion of equality between two flashcards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FlashCard)) {
            return false;
        }

        FlashCard otherFlashCard = (FlashCard) other;
        return otherFlashCard.getGermanPhrase().equals(getGermanPhrase())
                && otherFlashCard.getEnglishPhrase().equals(getEnglishPhrase())
                && otherFlashCard.getDifficultyTag().equals(getDifficultyTag())
                && otherFlashCard.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(germanPhrase, englishPhrase, difficultyTag, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getGermanPhrase())
                .append(" English phrase: ")
                .append(getEnglishPhrase())
                .append(" Difficulty: ")
                .append(getDifficultyTag())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    public void updateShowingEnglish(boolean showingEnglish) {
        this.showingEnglish = showingEnglish;
    }

    public boolean isShowingEnglish() {
        return showingEnglish;
    }

    public FlashCard copy() {
        return new FlashCard(germanPhrase, englishPhrase, difficultyTag, tags);
    }
}
