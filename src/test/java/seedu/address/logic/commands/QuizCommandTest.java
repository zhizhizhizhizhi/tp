package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashCards.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.FlashCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class QuizCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_hideAllEnglishWords() {
        for (FlashCard flashCard: expectedModel.getFilteredFlashCardList()) {
            FlashCard changeTo = flashCard.copy();
            changeTo.updateShowingEnglish(false);
            expectedModel.setFlashCard(flashCard, changeTo);
        }
        assertCommandSuccess(new QuizCommand(), model, QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
