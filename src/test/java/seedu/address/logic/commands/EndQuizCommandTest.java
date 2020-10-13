package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.FlashCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class EndQuizCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showAllEnglishWords() {
        for (FlashCard flashCard: model.getFilteredFlashCardList()) {
            FlashCard changeTo = flashCard.copy();
            changeTo.updateShowingEnglish(false);
            model.setFlashCard(flashCard, changeTo);
        }
        assertCommandSuccess(new QuizCommand(), model, QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
