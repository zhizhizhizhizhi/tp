package seedu.address.testutil;

import seedu.address.model.Glossary;
import seedu.address.model.flashcard.FlashCard;

/**
 * A utility class to help with building Glossary objects.
 * Example usage: <br>
 *     {@code Glossary ab = new GlossaryBuilder().withPerson("John", "Doe").build();}
 */
public class GlossaryBuilder {

    private Glossary glossary;

    public GlossaryBuilder() {
        glossary = new Glossary();
    }

    public GlossaryBuilder(Glossary Glossary) {
        this.glossary = glossary;
    }

    /**
     * Adds a new {@code FlashCard} to the {@code Glossary} that we are building.
     */
    public GlossaryBuilder withPerson(FlashCard flashCard) {
        glossary.addPerson(flashCard);
        return this;
    }

    public Glossary build() {
        return glossary;
    }
}
