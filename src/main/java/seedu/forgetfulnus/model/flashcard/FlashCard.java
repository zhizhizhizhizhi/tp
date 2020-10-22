package seedu.forgetfulnus.model.flashcard;

import static seedu.forgetfulnus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.forgetfulnus.logic.commands.AddCommand;
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
    private final DifficultyTag difficultyTag;
    private final Set<Tag> tags = new HashSet<>();
    private Order order;

    private boolean showingEnglish = true;

    /**
     * Every field must be present and not null.
     */
    public FlashCard(GermanPhrase germanPhrase, EnglishPhrase englishPhrase,
                     DifficultyTag difficultyTag, Set<Tag> tags) {
        requireAllNonNull(germanPhrase, englishPhrase, difficultyTag, tags);
        this.germanPhrase = germanPhrase;
        this.englishPhrase = englishPhrase;
        this.difficultyTag = difficultyTag;
        this.tags.addAll(tags);
        this.order = new Order(AddCommand.getNextOrderOfAddition());
    }

    /**
     * Used only for testing. Every field must be present and not null.
     */
    public FlashCard(GermanPhrase germanPhrase, EnglishPhrase englishPhrase,
                     DifficultyTag difficultyTag, Set<Tag> tags, Order order) {
        requireAllNonNull(germanPhrase, englishPhrase, difficultyTag, tags);
        this.germanPhrase = germanPhrase;
        this.englishPhrase = englishPhrase;
        this.difficultyTag = difficultyTag;
        this.tags.addAll(tags);
        this.order = order;
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

    public Order getOrder() {
        return order;
    }
    public FlashCard setOrder(int num) {
        order.setValue(num);
        return this;
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
     * Returns true if both flashcards have the same data fields, except for their Orders.
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
        assert !order.equals(otherFlashCard.getOrder()) : "Orders should never be the same!";
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
        return new FlashCard(germanPhrase, englishPhrase, difficultyTag, tags, order);
    }
}
