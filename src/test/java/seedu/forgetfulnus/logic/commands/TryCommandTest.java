package seedu.forgetfulnus.logic.commands;

import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class TryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new UserPrefs());
    }

    @Test
    public void execute_quizModeTest() {
        try {
            CommandResult test = new TryCommand("incorrect attempt!").executeWithChecks(model);
            assertEquals(test.toString(), TryCommand.QUIZMODE_REMINDER);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_indexCheck() throws CommandException {
        new QuizCommand().execute(model);
        new QuizCommand().execute(expectedModel);
        try {
            int index = model.getQuizModeIndex();
            int score = model.getQuizScore();
            int total = model.getQuizTotalQuestions();
            CommandResult test = new TryCommand("incorrect attempt!").executeWithChecks(model);
            assertEquals(test.toString(), TryCommand.INCORRECT_ATTEMPT);
            assertEquals(model.getQuizModeIndex(), index);
            assertEquals(model.getQuizScore(), score);
            assertEquals(model.getQuizTotalQuestions(), total);
            String correctAttempt = model.getFilteredFlashCardList().get(0).getEnglishPhrase().toString();
            CommandResult test2 = new TryCommand(correctAttempt).executeWithChecks(model);
            assertEquals(test2.toString(),
                    TryCommand.CORRECT_ATTEMPT
                            + new NextCommand().executeWithChecks(expectedModel));
            assertEquals(model.getQuizModeIndex(), index + 1);
            assertEquals(model.getQuizScore(), score + 1);
            assertEquals(model.getQuizTotalQuestions(), total + 1);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}
