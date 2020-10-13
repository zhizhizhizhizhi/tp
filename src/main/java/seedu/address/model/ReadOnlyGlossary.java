package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.FlashCard;

/**
 * Unmodifiable view of a glossary.
 */
public interface ReadOnlyGlossary {

    /**
     * Returns an unmodifiable view of the flashcards list.
     * This list will not contain any duplicate flashcard.
     */
    ObservableList<FlashCard> getFlashCardList();

}
