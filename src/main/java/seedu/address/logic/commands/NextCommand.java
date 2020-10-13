package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.FlashCard;

public class NextCommand extends Command {
    public static final String COMMAND_WORD = "next";
    public static final String QUIZMODE_REMINDER = "Command cannot be used when not in quiz mode. "
            + "Enter 'quiz' to start quizzing.";
    public static final String MESSAGE_SUCCESS = "next card: ";
    private static String germanWord;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        int index = model.getQuizModeIndex();
        if (index < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PHRASE_DISPLAYED_INDEX);
        } else if (!model.isQuizMode()) {
            return new CommandResult(QUIZMODE_REMINDER);
        } else if (model.getQuizModeIndex() < lastShownList.size()) {
            FlashCard toEdit = lastShownList.get(index);
            FlashCard changeTo = toEdit.copy();
            germanWord = toEdit.getGermanPhrase().fullGermanPhrase;
            changeTo.updateShowingEnglish(true);
            model.setFlashCard(toEdit, changeTo);
            model.updateQuizModeIndex(index + 1);
            model.updateFilteredPhraseList();
            return new CommandResult(String.format(MESSAGE_SUCCESS + germanWord));
        } else {
            Command endQuiz = new EndQuizCommand();
            return endQuiz.execute(model);
        }
    }
}
