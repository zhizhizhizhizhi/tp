package seedu.forgetfulnus.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * An Immutable Glossary that is serializable to JSON format.
 */
@JsonRootName(value = "glossary")
class JsonSerializableGlossary {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    private final List<JsonAdaptedFlashCard> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableGlossary(@JsonProperty("persons") List<JsonAdaptedFlashCard> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableGlossary(ReadOnlyGlossary source) {
        persons.addAll(source.getFlashCardList().stream().map(JsonAdaptedFlashCard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Glossary toModelType() throws IllegalValueException {
        Glossary glossary = new Glossary();
        for (JsonAdaptedFlashCard jsonAdaptedFlashCard : persons) {
            FlashCard flashCard = jsonAdaptedFlashCard.toModelType();
            if (glossary.hasFlashCard(flashCard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            glossary.addFlashCard(flashCard);
        }
        return glossary;
    }

}
