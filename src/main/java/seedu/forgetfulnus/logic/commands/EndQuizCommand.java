package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.ListIterator;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Ends the ongoing round of vocabulary self-testing.
 */
public class EndQuizCommand extends Command {

    public static final String COMMAND_WORD = "end";

    public static final String MESSAGE_SUCCESS = "Quiz ended!";
    public static final String QUIZ_MODE_REMINDER = "Quiz has ended.";

    private static final CommandType type = CommandType.QUIZ_MODE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.isRandomQuizMode()) {
            model.setRandomQuizMode(false);
        }
        if (model.isQuizMode()) {
            List<FlashCard> lastShownList = model.getFilteredFlashCardList();
            ListIterator<FlashCard> iterator = lastShownList.listIterator();
            while (iterator.hasNext()) {
                FlashCard toEdit = iterator.next();
                FlashCard changeTo = toEdit.copy();
                changeTo.updateShowingEnglish(true);
                model.setFlashCard(toEdit, changeTo);
            }
            model.setQuizMode(false);
            model.updateFilteredPhraseList(unused -> true);
            return new CommandResult(String.format(MESSAGE_SUCCESS
                            + " Your score: %s / %s",
                    model.getQuizScore(), model.getQuizTotalQuestions()));
        } else {
            return new CommandResult(QUIZ_MODE_REMINDER);
        }
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
