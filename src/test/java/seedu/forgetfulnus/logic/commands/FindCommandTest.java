package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_PHRASES_LISTED_OVERVIEW;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.FRIDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.SATURDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.WEDNESDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.flashcard.GermanPhraseContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalGlossary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalGlossary(), new UserPrefs());

    @Test
    public void equals() {
        GermanPhraseContainsKeywordsPredicate firstPredicate =
                new GermanPhraseContainsKeywordsPredicate(Collections.singletonList("first"));
        GermanPhraseContainsKeywordsPredicate secondPredicate =
                new GermanPhraseContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashCardFound() {
        String expectedMessage = String.format(MESSAGE_PHRASES_LISTED_OVERVIEW, 0);
        GermanPhraseContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPhraseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashCardsFound() {
        String expectedMessage = String.format(MESSAGE_PHRASES_LISTED_OVERVIEW, 3);
        GermanPhraseContainsKeywordsPredicate predicate =
                preparePredicate("Mittwoch Freitag Samstag");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPhraseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(WEDNESDAY, FRIDAY, SATURDAY), model.getFilteredFlashCardList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private GermanPhraseContainsKeywordsPredicate preparePredicate(String userInput) {
        return new GermanPhraseContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
