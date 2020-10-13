package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

public class NextCommand extends Command {

    public static final String COMMAND_WORD = "next";
    public static final String QUIZMODE_REMINDER = "Command cannot be used when not in quiz mode. "
            + "Enter 'quiz' to start quizzing.";
    public static final String MESSAGE_SUCCESS = "Next card: ";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String germanWord;
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        int index = model.getQuizModeIndex();
        if (index < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        } else if (!model.isQuizMode()) {
            return new CommandResult(QUIZMODE_REMINDER);
        } else if (model.getQuizModeIndex() < lastShownList.size()) {
            FlashCard toEdit = lastShownList.get(index);
            FlashCard nextCard = index == lastShownList.size() - 1 ? null : lastShownList.get(index + 1);
            FlashCard changeTo = toEdit.copy();
            germanWord = nextCard == null ? "End of quiz!" : nextCard.getGermanPhrase().fullGermanPhrase;
            changeTo.updateShowingEnglish(true);
            model.setFlashCard(toEdit, changeTo);
            model.updateQuizModeIndex(index + 1);
            model.updateFilteredPhraseList();
            return new CommandResult(MESSAGE_SUCCESS + germanWord);
        } else {
            Command endQuiz = new EndQuizCommand();
            return endQuiz.execute(model);
        }
    }
}
