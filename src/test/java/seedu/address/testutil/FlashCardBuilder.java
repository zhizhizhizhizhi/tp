package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.flashcard.EnglishPhrase;
import seedu.address.model.flashcard.FlashCard;
import seedu.address.model.flashcard.GermanPhrase;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building FlashCard objects.
 */
public class FlashCardBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "englishphrase1";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private GermanPhrase germanPhrase;
    private EnglishPhrase englishPhrase;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashCardBuilder} with the default details.
     */
    public FlashCardBuilder() {
        germanPhrase = new GermanPhrase(DEFAULT_NAME);
        englishPhrase = new EnglishPhrase(DEFAULT_PHONE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashCardBuilder with the data of {@code flashCardToCopy}.
     */
    public FlashCardBuilder(FlashCard flashCardToCopy) {
        germanPhrase = flashCardToCopy.getGermanPhrase();
        englishPhrase = flashCardToCopy.getEnglishPhrase();
        tags = new HashSet<>(flashCardToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withName(String name) {
        this.germanPhrase = new GermanPhrase(name);
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
     * Sets the {@code Phone} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withPhone(String phone) {
        this.englishPhrase = new EnglishPhrase(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withEmail(String email) {
        return this;
    }

    public FlashCard build() {
        return new FlashCard(germanPhrase, englishPhrase, tags);
    }

}
