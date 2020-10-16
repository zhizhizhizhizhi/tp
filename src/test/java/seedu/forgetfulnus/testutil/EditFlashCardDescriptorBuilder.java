package seedu.forgetfulnus.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.forgetfulnus.logic.commands.EditCommand.EditFlashCardDescriptor;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * A utility class to help with building EditFlashCardDescriptor objects.
 */
public class EditFlashCardDescriptorBuilder {

    private EditFlashCardDescriptor descriptor;

    public EditFlashCardDescriptorBuilder() {
        descriptor = new EditFlashCardDescriptor();
    }

    public EditFlashCardDescriptorBuilder(EditFlashCardDescriptor descriptor) {
        this.descriptor = new EditFlashCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFlashCardDescriptor} with fields containing {@code flashCard}'s details
     */
    public EditFlashCardDescriptorBuilder(FlashCard flashCard) {
        descriptor = new EditFlashCardDescriptor();
        descriptor.setGermanPhrase(flashCard.getGermanPhrase());
        descriptor.setEnglishPhrase(flashCard.getEnglishPhrase());
        descriptor.setTags(flashCard.getTags());
    }

    /**
     * Sets the {@code German Phrase} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withGermanPhrase(String gphrase) {
        descriptor.setGermanPhrase(new GermanPhrase(gphrase));
        return this;
    }

    /**
     * Sets the {@code English Phrase} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withEnglishPhrase(String ephrase) {
        descriptor.setEnglishPhrase(new EnglishPhrase(ephrase));
        return this;
    }

    /**
     * Sets the {@code Difficulty Tag} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withDifficultyTag(String difficultyTag) {
        descriptor.setDifficultyTag(new DifficultyTag(difficultyTag));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFlashCardDescriptor}
     * that we are building.
     */
    public EditFlashCardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditFlashCardDescriptor build() {
        return descriptor;
    }
}
