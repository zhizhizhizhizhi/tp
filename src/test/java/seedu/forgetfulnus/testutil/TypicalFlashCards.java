package seedu.forgetfulnus.testutil;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_DIFFICULTY_TAG_MEDIUM;
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
            .withDifficultyTag("EASY")
            .withTags("day")
            .withOrder(1).build();
    public static final FlashCard TUESDAY = new FlashCardBuilder().withGermanPhrase("Dienstag")
            .withEnglishPhrase("Tuesday")
            .withDifficultyTag("EASY")
            .withTags("day")
            .withOrder(2).build();
    public static final FlashCard WEDNESDAY = new FlashCardBuilder().withGermanPhrase("Mittwoch")
            .withEnglishPhrase("Wednesday")
            .withDifficultyTag("MEDIUM")
            .withOrder(3).build();
    public static final FlashCard THURSDAY = new FlashCardBuilder().withGermanPhrase("Donnerstag")
            .withEnglishPhrase("Thursday")
            .withDifficultyTag("MEDIUM")
            .withOrder(4).withTags("day").build();
    public static final FlashCard FRIDAY = new FlashCardBuilder().withGermanPhrase("Freitag")
            .withEnglishPhrase("Friday")
            .withDifficultyTag("MEDIUM")
            .withOrder(5).build();
    public static final FlashCard SATURDAY = new FlashCardBuilder().withGermanPhrase("Samstag")
            .withEnglishPhrase("Saturday")
            .withDifficultyTag("HARD")
            .withOrder(6).build();
    public static final FlashCard SUNDAY = new FlashCardBuilder().withGermanPhrase("Sonntag")
            .withEnglishPhrase("Sunday")
            .withDifficultyTag("HARD")
            .withOrder(7).build();

    // Manually added
    public static final FlashCard MORNING = new FlashCardBuilder().withGermanPhrase("Morgen")
            .withEnglishPhrase("Morning")
            .withDifficultyTag("Medium")
            .withOrder(8).build();
    public static final FlashCard NOON = new FlashCardBuilder().withGermanPhrase("Mittag").withEnglishPhrase("Noon")
            .withDifficultyTag("Medium")
            .withOrder(9).build();

    // Manually added - FlashCard's details found in {@code CommandTestUtil}
    public static final FlashCard FORGETFULNESS = new FlashCardBuilder()
            .withGermanPhrase(VALID_GERMAN_PHRASE_FORGETFULNESS)
            .withEnglishPhrase(VALID_ENGLISH_PHRASE_FORGETFULNESS)
            .withDifficultyTag(VALID_DIFFICULTY_TAG_MEDIUM)
            .withTags().withOrder(1).build();
    public static final FlashCard TABLE = new FlashCardBuilder()
            .withGermanPhrase(VALID_GERMAN_PHRASE_TABLE)
            .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
            .withDifficultyTag(VALID_DIFFICULTY_TAG_MEDIUM)
            .withTags(VALID_TAG_CHAPTER_ONE, VALID_TAG_HARD).withOrder(2).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns a {@code Glossary} with all the typical flashcards.
     */
    public static Glossary getTypicalGlossary() {
        Glossary g = new Glossary();
        for (FlashCard flashCard : getTypicalFlashCards()) {
            g.addFlashCard(flashCard);
        }
        return g;
    }

    /**
     * Returns a sorted {@code Glossary} with all the typical flashcards.
     */
    public static Glossary getTypicalSortedGlossary() {
        Glossary g = new Glossary();
        for (FlashCard flashCard : getTypicalGermanSortedFlashCards()) {
            g.addFlashCard(flashCard);
        }
        return g;
    }
    /**
     * A List of FlashCards sorted by chronological order.
     */
    public static List<FlashCard> getTypicalFlashCards() {
        return new ArrayList<>(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY));
    }

    /**
     * A List of FlashCards sorted by alphabetical order of their German Phrases.
     */
    public static List<FlashCard> getTypicalGermanSortedFlashCards() {
        return new ArrayList<>(Arrays.asList(TUESDAY, THURSDAY, FRIDAY, WEDNESDAY, MONDAY, SATURDAY, SUNDAY));
    }
}
