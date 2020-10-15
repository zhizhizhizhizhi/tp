package seedu.forgetfulnus.logic.commands;

import seedu.forgetfulnus.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    private static final CommandType type = CommandType.ANY_MODE;

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
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
