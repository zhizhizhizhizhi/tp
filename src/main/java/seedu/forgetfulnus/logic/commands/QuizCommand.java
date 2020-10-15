package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

public class QuizCommand extends Command {
    private static final CommandType type = CommandType.NOT_QUIZ_MODE;
    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "Quiz started!";
    public static final String QUIZMODE_REMINDER = "Quiz has started. "
            + "Enter 'end' to end quizzing.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        ListIterator<FlashCard> iterator = lastShownList.listIterator();
        while (iterator.hasNext()) {
            FlashCard toEdit = iterator.next();
            FlashCard changeTo = toEdit.copy();
            changeTo.updateShowingEnglish(false);
            model.setFlashCard(toEdit, changeTo);
        }
        model.setQuizMode(true);
        model.updateFilteredPhraseList();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZMODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
