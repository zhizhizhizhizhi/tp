package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.flashcard.EnglishPhrase;
import seedu.address.model.flashcard.GermanPhrase;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String German phrase} into a {@code German Phrase}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code German Phrase} is invalid.
     */
    public static GermanPhrase parseGermanPhrase(String germanPhrase) throws ParseException {
        requireNonNull(germanPhrase);
        String trimmedGermanPhrase = germanPhrase.trim();
        if (!GermanPhrase.isValidGermanPhrase(trimmedGermanPhrase)) {
            throw new ParseException(GermanPhrase.MESSAGE_CONSTRAINTS);
        }
        return new GermanPhrase(trimmedGermanPhrase);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static EnglishPhrase parseEnglishPhrase(String englishPhrase) throws ParseException {
        requireNonNull(englishPhrase);
        String trimmedEnglishPhrase = englishPhrase.trim();
        if (!EnglishPhrase.isValidEnglishPhrase(trimmedEnglishPhrase)) {
            throw new ParseException(EnglishPhrase.MESSAGE_CONSTRAINTS);
        }
        return new EnglishPhrase(trimmedEnglishPhrase);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
