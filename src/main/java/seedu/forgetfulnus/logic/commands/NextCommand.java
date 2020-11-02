package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Skips the current flashcard during a vocabulary self-testing session.
 */
public class NextCommand extends Command {

    public static final String COMMAND_WORD = "next";

    public static final String QUIZ_MODE_REMINDER = COMMAND_WORD + " command cannot be used when not in quiz mode. "
            + "Enter 'quiz' to start quizzing.";

    public static final String MESSAGE_SUCCESS = "Enter the definition of: ";

    private static final CommandType type = CommandType.QUIZ_MODE;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String germanWord;
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        int index = model.getQuizModeIndex();
        if (index < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        } else if (index < lastShownList.size() - 1) {
            FlashCard toEdit = lastShownList.get(index);
            FlashCard nextCard = lastShownList.get(index + 1);
            assert (nextCard != null);
            FlashCard changeTo = toEdit.copy();
            germanWord = nextCard.getGermanPhrase().toString();
            changeTo.updateShowingEnglish(true);
            model.setFlashCard(toEdit, changeTo);
            model.addCardToScore(changeTo);
            model.updateFilteredPhraseList();
            return new CommandResult(MESSAGE_SUCCESS + germanWord);
        } else {
            Command endQuiz = new EndQuizCommand();
            return endQuiz.executeWithChecks(model);
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
