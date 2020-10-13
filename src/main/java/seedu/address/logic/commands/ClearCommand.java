package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Glossary;
import seedu.address.model.Model;

/**
 * Clears the glossary.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Glossary has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setGlossary(new Glossary());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
