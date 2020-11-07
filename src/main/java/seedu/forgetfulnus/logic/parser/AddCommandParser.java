package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_MULTIPLE_PREFIX;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GENDER_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.PredefinedTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    public static final String MESSAGE_INVALID_CLASS_CAST = "Invalid casting of class";
    private static Logger logger = Logger.getLogger("AddCommandParserLogger");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws ClassCastException if wrong class is cast
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE,
                                PREFIX_DIFFICULTY_TAG, PREFIX_GENDER_TAG, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.isSingleValue(PREFIX_GERMAN_PHRASE)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        }

        GermanPhrase germanPhrase = ParserUtil.parseGermanPhrase(argMultimap.getValue(PREFIX_GERMAN_PHRASE).get());

        if (!argMultimap.isSingleValue(PREFIX_ENGLISH_PHRASE)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        }

        EnglishPhrase englishPhrase = ParserUtil.parseEnglishPhrase(argMultimap.getValue(PREFIX_ENGLISH_PHRASE).get());

        DifficultyTag difficultyTag;

        if (!arePrefixesPresent(argMultimap, PREFIX_DIFFICULTY_TAG)) {
            logger.log(Level.INFO, "Set default difficulty tag, MEDIUM Difficulty.");
            difficultyTag = new DifficultyTag(DifficultyTag.MEDIUM_TAG);
        } else if (!argMultimap.isSingleValue(PREFIX_DIFFICULTY_TAG)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        } else {

            PredefinedTag newTag = ParserUtil.parsePredefinedTag(PREFIX_DIFFICULTY_TAG,
                    argMultimap.getValue(PREFIX_DIFFICULTY_TAG).get());

            if (newTag instanceof DifficultyTag) {
                difficultyTag = (DifficultyTag) newTag;
            } else {
                throw new ClassCastException(MESSAGE_INVALID_CLASS_CAST);
            }
        }

        GenderTag genderTag;

        if (!arePrefixesPresent(argMultimap, PREFIX_GENDER_TAG)) {
            logger.log(Level.INFO, "Set default gender tag, NONE Gender.");
            genderTag = new GenderTag(GenderTag.NONE_GENDER_TAG);
        } else if (!argMultimap.isSingleValue(PREFIX_GENDER_TAG)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        } else {

            PredefinedTag newTag = ParserUtil.parsePredefinedTag(PREFIX_GENDER_TAG,
                    argMultimap.getValue(PREFIX_GENDER_TAG).get());

            if (newTag instanceof GenderTag) {
                genderTag = (GenderTag) newTag;
            } else {
                throw new ClassCastException(MESSAGE_INVALID_CLASS_CAST);
            }
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        FlashCard flashCard = new FlashCard(germanPhrase, englishPhrase, difficultyTag, genderTag, tagList);

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
