package seedu.forgetfulnus.testutil;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard MONDAY = new FlashCardBuilder().withGermanPhrase("Montag")
            .withEnglishPhrase("Monday")
            .withTags("day").build();
    public static final FlashCard TUESDAY = new FlashCardBuilder().withGermanPhrase("Dienstag")
            .withEnglishPhrase("Tuesday")
            .withTags("day", "hard").build();
    public static final FlashCard WEDNESDAY = new FlashCardBuilder().withGermanPhrase("Mittwoch")
            .withEnglishPhrase("Wednesday")
            .withTags("day", "very hard").build();
    public static final FlashCard THUSRDAY = new FlashCardBuilder().withGermanPhrase("Donnerstag")
            .withEnglishPhrase("Thursday")
            .withTags("day").build();
    public static final FlashCard FRIDAY = new FlashCardBuilder().withGermanPhrase("Freitag")
            .withEnglishPhrase("Friday")
            .withTags("day").build();
    public static final FlashCard SATURDAY = new FlashCardBuilder().withGermanPhrase("Samstag")
            .withEnglishPhrase("Saturday")
            .withTags("day").build();
    public static final FlashCard SUNDAY = new FlashCardBuilder().withGermanPhrase("Sonntag")
            .withEnglishPhrase("Sunday")
            .withTags("day").build();

    // Manually added
    public static final FlashCard MORNING = new FlashCardBuilder().withGermanPhrase("Morgen")
            .withEnglishPhrase("Morning")
            .build();
    public static final FlashCard NOON = new FlashCardBuilder().withGermanPhrase("Mittag").withEnglishPhrase("Noon")
            .build();

    // Manually added - FlashCard's details found in {@code CommandTestUtil}
    public static final FlashCard FORGETFULNESS = new FlashCardBuilder()
            .withGermanPhrase(VALID_GERMAN_PHRASE_FORGETFULNESS)
            .withEnglishPhrase(VALID_ENGLISH_PHRASE_FORGETFULNESS)
            .withTags(VALID_TAG_HARD).build();
    public static final FlashCard TABLE = new FlashCardBuilder().withGermanPhrase(VALID_GERMAN_PHRASE_TABLE)
            .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
            .withTags(VALID_TAG_CHAPTER_ONE, VALID_TAG_HARD).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns an {@code Glossary} with all the typical flashcards.
     */
    public static Glossary getTypicalGlossary() {
        Glossary g = new Glossary();
        for (FlashCard flashCard : getTypicalFlashCards()) {
            g.addFlashCard(flashCard);
        }
        return g;
    }

    public static List<FlashCard> getTypicalFlashCards() {
        return new ArrayList<>(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THUSRDAY, FRIDAY, SATURDAY, SUNDAY));
    }
}
