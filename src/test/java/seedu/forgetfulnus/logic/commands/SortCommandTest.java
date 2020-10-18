package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.SortCommand.MESSAGE_SORT_SUCCESS;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.flashcard.FlashCard;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new UserPrefs());
    }

    @Test
    public void constructor_invalidString_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new SortCommand("invalid string"));
        assertThrows(AssertionError.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_stringGerman_success() {
        SortCommand command = new SortCommand("german");
        CommandResult result = command.execute(model);
        List<FlashCard> expectedList = new ArrayList<>(expectedModel.getGlossary().getFlashCardList());
        expectedList.sort(SortCommand.GERMAN_COMP);
        assertTrue(checkSortedOrder(model.getGlossary().getFlashCardList(), expectedList));
        assertEquals(result, new CommandResult(MESSAGE_SORT_SUCCESS));
    }

    @Test
    public void execute_stringEnglish_success() {
        SortCommand command = new SortCommand("english");
        CommandResult result = command.execute(model);
        List<FlashCard> expectedList = new ArrayList<>(expectedModel.getGlossary().getFlashCardList());
        expectedList.sort(SortCommand.ENGLISH_COMP);
        assertTrue(checkSortedOrder(model.getGlossary().getFlashCardList(), expectedList));
        assertEquals(result, new CommandResult(MESSAGE_SORT_SUCCESS));
    }

    /**
     * Checks whether the order in two lists are exactly the same.
     * @param list1 first list to compare.
     * @param list2 second list to compare
     * @return true if the orders of the lists are the same, false otherwise.
     */
    private boolean checkSortedOrder(List<FlashCard> list1, List<FlashCard> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
