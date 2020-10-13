package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Glossary;
import seedu.address.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard ALICE = new FlashCardBuilder().withName("Alice Pauline")
            .withPhone("englishPhrase")
            .withTags("friends").build();
    public static final FlashCard BENSON = new FlashCardBuilder().withName("Benson Meier")
            .withPhone("englishPhrase")
            .withTags("owesMoney", "friends").build();
    public static final FlashCard CARL = new FlashCardBuilder().withName("Carl Kurz").withPhone("englishPhrase")
            .build();
    public static final FlashCard DANIEL = new FlashCardBuilder().withName("Daniel Meier").withPhone("englishPhrase")
            .withTags("friends").build();
    public static final FlashCard ELLE = new FlashCardBuilder().withName("Elle Meyer").withPhone("englishPhrase")
            .build();
    public static final FlashCard FIONA = new FlashCardBuilder().withName("Fiona Kunz").withPhone("englishPhrase")
            .build();
    public static final FlashCard GEORGE = new FlashCardBuilder().withName("George Best").withPhone("englishPhrase")
            .build();

    // Manually added
    public static final FlashCard HOON = new FlashCardBuilder().withName("Hoon Meier").withPhone("englishPhrase")
            .build();
    public static final FlashCard IDA = new FlashCardBuilder().withName("Ida Mueller").withPhone("englishPhrase")
            .build();

    // Manually added - FlashCard's details found in {@code CommandTestUtil}
    public static final FlashCard AMY = new FlashCardBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final FlashCard BOB = new FlashCardBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns an {@code Glossary} with all the typical flashcards.
     */
    public static Glossary getTypicalGlossary() {
        Glossary ab = new Glossary();
        for (FlashCard flashCard : getTypicalFlashCards()) {
            ab.addFlashCard(flashCard);
        }
        return ab;
    }

    public static List<FlashCard> getTypicalFlashCards() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
