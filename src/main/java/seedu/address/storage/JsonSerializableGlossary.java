package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Glossary;
import seedu.address.model.ReadOnlyGlossary;
import seedu.address.model.flashcard.FlashCard;

/**
 * An Immutable Glossary that is serializable to JSON format.
 */
@JsonRootName(value = "glossary")
class JsonSerializableGlossary {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate flashcard(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableGlossary(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableGlossary(ReadOnlyGlossary source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Glossary toModelType() throws IllegalValueException {
        Glossary glossary = new Glossary();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            FlashCard flashCard = jsonAdaptedPerson.toModelType();
            if (glossary.hasPerson(flashCard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            glossary.addPerson(flashCard);
        }
        return glossary;
    }

}
