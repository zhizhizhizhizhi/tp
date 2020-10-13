package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalFlashCards.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.FlashCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class NextCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_quizModeReminderCheck() {
        assertCommandSuccess(new NextCommand(), model, NextCommand.QUIZMODE_REMINDER, expectedModel);
    }

    @Test
    public void execute_indexCheck(){
        new QuizCommand().execute(model);
        Assertions.assertEquals(model.getQuizModeIndex(), 0);

        try {
            model.updateQuizModeIndex(-1);
            new NextCommand().execute(model);
        } catch (CommandException e) {
            Assertions.assertEquals(e.getMessage(), Messages.MESSAGE_INVALID_PHRASE_DISPLAYED_INDEX);
        }

        model.updateQuizModeIndex(0);
        try {
            for (FlashCard f : model.getFilteredFlashCardList()) {
                new NextCommand().execute(model);
            }
            Assertions.assertEquals(model.getQuizModeIndex(), model.getFilteredFlashCardList().size());

            new NextCommand().execute(model);
            Assertions.assertEquals(model.getQuizModeIndex(), 0);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
