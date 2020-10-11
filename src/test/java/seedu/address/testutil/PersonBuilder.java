package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.EnglishPhrase;
import seedu.address.model.person.GermanPhrase;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "englishphrase1";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private GermanPhrase germanPhrase;
    private EnglishPhrase englishPhrase;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        germanPhrase = new GermanPhrase(DEFAULT_NAME);
        englishPhrase = new EnglishPhrase(DEFAULT_PHONE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        germanPhrase = personToCopy.getGermanPhrase();
        englishPhrase = personToCopy.getEnglishPhrase();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.germanPhrase = new GermanPhrase(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.englishPhrase = new EnglishPhrase(phone);
        return this;
    }

    public Person build() {
        return new Person(germanPhrase, englishPhrase, tags);
    }

}
