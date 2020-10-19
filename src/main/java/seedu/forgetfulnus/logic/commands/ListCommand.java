package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.forgetfulnus.model.Model;

/**
 * Lists all phrases in the address book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards.";
    public static final String QUIZMODE_REMINDER = "'list' command cannot be used in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
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