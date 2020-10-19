package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE, PREFIX_DIFFICULTY_TAG,
                                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        DifficultyTag difficultyTag;

        if (!arePrefixesPresent(argMultimap, PREFIX_DIFFICULTY_TAG)) {
            difficultyTag = new DifficultyTag();
        } else {
            difficultyTag = ParserUtil.parseDifficultyTag(argMultimap.getValue(PREFIX_DIFFICULTY_TAG).get());
        }

        GermanPhrase germanPhrase = ParserUtil.parseGermanPhrase(argMultimap.getValue(PREFIX_GERMAN_PHRASE).get());
        EnglishPhrase englishPhrase = ParserUtil.parseEnglishPhrase(argMultimap.getValue(PREFIX_ENGLISH_PHRASE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        FlashCard flashCard = new FlashCard(germanPhrase, englishPhrase, difficultyTag, tagList);

        return new AddCommand(flashCard);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
