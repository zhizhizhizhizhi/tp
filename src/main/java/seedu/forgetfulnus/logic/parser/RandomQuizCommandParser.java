package seedu.forgetfulnus.logic.parser;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.RandomQuizCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
