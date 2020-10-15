package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.forgetfulnus.model.Model;

/**
 * Lists all phrases in the address book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";

    private static final CommandType type = CommandType.ANY_MODE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        new EndQuizCommand().execute(model);
        model.updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String getQuizModeReminder() {
        return "";
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
