package seedu.forgetfulnus.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.Tag;
import seedu.forgetfulnus.model.util.SampleDataUtil;

import static seedu.forgetfulnus.model.tag.DifficultyTag.MEDIUM_TAG;
import static seedu.forgetfulnus.model.tag.DifficultyTag.isValidDifficultyTag;

/**
 * A utility class to help with building FlashCard objects.
 */
public class FlashCardBuilder {

    public static final String DEFAULT_GERMAN_PHRASE = "Vergesslichkeit";
    public static final String DEFAULT_ENGLISH_PHRASE = "Forgetfulness";

    private GermanPhrase germanPhrase;
    private EnglishPhrase englishPhrase;
    private DifficultyTag difficultyTag;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashCardBuilder} with the default details.
     */
    public FlashCardBuilder() {
        germanPhrase = new GermanPhrase(DEFAULT_GERMAN_PHRASE);
        englishPhrase = new EnglishPhrase(DEFAULT_ENGLISH_PHRASE);
        difficultyTag = new DifficultyTag();
        assert difficultyTag.toString().equals(MEDIUM_TAG);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashCardBuilder with the data of {@code flashCardToCopy}.
     */
    public FlashCardBuilder(FlashCard flashCardToCopy) {
        germanPhrase = flashCardToCopy.getGermanPhrase();
        englishPhrase = flashCardToCopy.getEnglishPhrase();
        difficultyTag = flashCardToCopy.getDifficultyTag();
        assert isValidDifficultyTag(difficultyTag.toString());
        tags = new HashSet<>(flashCardToCopy.getTags());
    }

    /**
     * Sets the {@code gphrase} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withGermanPhrase(String gphrase) {
        this.germanPhrase = new GermanPhrase(gphrase);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ephrase} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withEnglishPhrase(String ephrase) {
        this.englishPhrase = new EnglishPhrase(ephrase);
        return this;
    }

    /**
     * Sets the {@code difficultyTag} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withDifficultyTag(String difficultyTag) {
        this.difficultyTag = new DifficultyTag(difficultyTag);
        return this;
    }

    public FlashCard build() {
        return new FlashCard(germanPhrase, englishPhrase, difficultyTag, tags);
    }

}
