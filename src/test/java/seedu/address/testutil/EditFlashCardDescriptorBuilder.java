package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditFlashCardDescriptor;
import seedu.address.model.flashcard.EnglishPhrase;
import seedu.address.model.flashcard.FlashCard;
import seedu.address.model.flashcard.GermanPhrase;
import seedu.address.model.tag.Tag;

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
     * Sets the {@code Name} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withName(String name) {
        descriptor.setGermanPhrase(new GermanPhrase(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withPhone(String phone) {
        descriptor.setEnglishPhrase(new EnglishPhrase(phone));
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
