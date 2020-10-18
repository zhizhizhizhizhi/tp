package seedu.forgetfulnus.logic.commands;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class TryCommand extends Command {
    public static final String COMMAND_WORD = "try";
    public static final String QUIZMODE_REMINDER = "Command cannot be used when not in quiz mode. "
            + "Enter 'quiz' to start quizzing.";
    public static final String CORRECT_ATTEMPT = "Correct! ";
    public static final String INCORRECT_ATTEMPT = "Not correct. " +
            "Enter 'try <english phrase>' to try again " +
            "or 'next' to skip to next card";

    private static final CommandType type = CommandType.QUIZ_MODE;

    private final String attempt;

    /**
     * Creates a TryCommand to compare the quiz attempt with the flashcard's phrase.
     */
    public TryCommand(String attempt) {
        requireNonNull(attempt);
        this.attempt = attempt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        int index = model.getQuizModeIndex();
        if (index < 0 || index >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }
        FlashCard flashCard = lastShownList.get(index);
        assert (flashCard != null);
        if (flashCard.getEnglishPhrase().isCorrectAttempt(attempt)) {
            model.updateWithCorrectAttempt();
            return new CommandResult(CORRECT_ATTEMPT
                    + new NextCommand().executeWithChecks(model).toString());
        }
        return new CommandResult(INCORRECT_ATTEMPT);
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
