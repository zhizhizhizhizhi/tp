package seedu.forgetfulnus.logic.parser;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.DeleteCommand;
import seedu.forgetfulnus.logic.commands.RandomQuizCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class RandomQuizCommandParser implements Parser<RandomQuizCommand> {

    public RandomQuizCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RandomQuizCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RandomQuizCommand.MESSAGE_USAGE), pe);
        }
    }
}
