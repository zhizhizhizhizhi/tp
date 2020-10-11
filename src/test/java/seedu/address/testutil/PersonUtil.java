package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_GERMAN_PHRASE + person.getGermanPhrase().fullGermanPhrase + " ");
        sb.append(PREFIX_ENGLISH_PHRASE + person.getEnglishPhrase().fullEnglishPhrase + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getGermanPhrase()
                .ifPresent(germanPhrase -> sb.append(PREFIX_GERMAN_PHRASE)
                        .append(germanPhrase.fullGermanPhrase).append(" "));
        descriptor.getEnglishPhrase()
                .ifPresent(englishPhrase -> sb.append(PREFIX_ENGLISH_PHRASE)
                        .append(englishPhrase.fullEnglishPhrase).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
