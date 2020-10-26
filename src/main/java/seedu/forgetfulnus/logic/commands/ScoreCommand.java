package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.forgetfulnus.model.Model;

public class ScoreCommand extends Command {

    public static final String COMMAND_WORD = "scores";

    public static final String QUIZMODE_REMINDER = "'score' command cannot be used in quiz mode. "
            + "Enter 'end' to end quizzing.";
    private static final CommandType type = CommandType.NOT_QUIZ_MODE;
    private static final String MESSAGE_HEADER = "Here are the scores for your past quiz attempts: ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        StringBuilder sb = new StringBuilder();
        String scores = model.getScoreList().asViewable();
        sb.append(MESSAGE_HEADER);
        sb.append(scores);
        return new CommandResult(sb.toString());
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
