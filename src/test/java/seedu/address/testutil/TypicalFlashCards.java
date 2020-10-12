package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard ALICE = new FlashCardBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("englishPhrase")
            .withTags("friends").build();
    public static final FlashCard BENSON = new FlashCardBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("englishPhrase")
            .withTags("owesMoney", "friends").build();
    public static final FlashCard CARL = new FlashCardBuilder().withName("Carl Kurz").withPhone("englishPhrase")
            .withEmail("heinz@example.com").build();
    public static final FlashCard DANIEL = new FlashCardBuilder().withName("Daniel Meier").withPhone("englishPhrase")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final FlashCard ELLE = new FlashCardBuilder().withName("Elle Meyer").withPhone("englishPhrase")
            .withEmail("werner@example.com").build();
    public static final FlashCard FIONA = new FlashCardBuilder().withName("Fiona Kunz").withPhone("englishPhrase")
            .withEmail("lydia@example.com").build();
    public static final FlashCard GEORGE = new FlashCardBuilder().withName("George Best").withPhone("englishPhrase")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final FlashCard HOON = new FlashCardBuilder().withName("Hoon Meier").withPhone("englishPhrase")
            .withEmail("stefan@example.com").build();
    public static final FlashCard IDA = new FlashCardBuilder().withName("Ida Mueller").withPhone("englishPhrase")
            .withEmail("hans@example.com").build();

    // Manually added - FlashCard's details found in {@code CommandTestUtil}
    public static final FlashCard AMY = new FlashCardBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final FlashCard BOB = new FlashCardBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (FlashCard flashCard : getTypicalPersons()) {
            ab.addPerson(flashCard);
        }
        return ab;
    }

    public static List<FlashCard> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
