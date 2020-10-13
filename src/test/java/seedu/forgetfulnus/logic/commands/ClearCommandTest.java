package seedu.forgetfulnus.logic.commands;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyGlossary_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGlossary_success() {
        Model model = new ModelManager(getTypicalGlossary(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalGlossary(), new UserPrefs());
        expectedModel.setGlossary(new Glossary());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
