package seedu.forgetfulnus.logic.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.RandomQuizCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses the given {@code String} of arguments in the context of the RandomQuizCommand
 * and returns a RandomQuizCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public class RandomQuizCommandParser implements Parser<RandomQuizCommand> {

    private static Logger logger = Logger.getLogger("RandomQuizCommandParserLogger");

    public RandomQuizCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Start processing arguments");
        try {
            Index index = ParserUtil.parseIndex(args);
            assert index.getOneBased() > 0 : "Parameter of RandomQuizCommand must be more than 0";
            logger.log(Level.INFO, "End of processing");
            return new RandomQuizCommand(index);
        } catch (ParseException pe) {
            logger.log(Level.WARNING, "Processing error");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RandomQuizCommand.MESSAGE_USAGE), pe);
        }
    }
}
