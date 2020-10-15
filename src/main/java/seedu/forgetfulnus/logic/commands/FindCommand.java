package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.GermanPhraseContainsKeywordsPredicate;

/**
 * Finds and lists all phrases in glossary which contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all phrases which contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private static final CommandType type = CommandType.ANY_MODE;
    private final GermanPhraseContainsKeywordsPredicate predicate;

    public FindCommand(GermanPhraseContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPhraseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PHRASES_LISTED_OVERVIEW, model.getFilteredFlashCardList().size()));
    }

    @Override
    public String getQuizModeReminder() {
        return "";
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
