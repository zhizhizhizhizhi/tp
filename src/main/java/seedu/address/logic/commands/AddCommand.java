package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.FlashCard;

/**
 * Adds a phrase to the glossary.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a phrase to the glossary. "
            + "Parameters: "
            + PREFIX_GERMAN_PHRASE + "GERMAN PHRASE "
            + PREFIX_ENGLISH_PHRASE + "ENGLISH PHRASE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GERMAN_PHRASE + "Vergesslichkeit "
            + PREFIX_ENGLISH_PHRASE + "Forgetfulness "
            + PREFIX_TAG + "hard ";

    public static final String MESSAGE_SUCCESS = "New phrase added: %1$s";
    public static final String MESSAGE_DUPLICATE_PHRASE = "This phrase already exists in the glossary";

    private final FlashCard toAdd;

    /**
     * Creates an AddCommand to add the specified {@code FlashCard}
     */
    public AddCommand(FlashCard flashCard) {
        requireNonNull(flashCard);
        toAdd = flashCard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PHRASE);
        }

        model.addFlashCard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
