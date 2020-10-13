package seedu.forgetfulnus.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Glossary} with sample data.
 */
public class SampleDataUtil {
    public static FlashCard[] getSampleFlashCards() {
        return new FlashCard[] {
            new FlashCard(new GermanPhrase("Montag"), new EnglishPhrase("Monday"),
                    getTagSet("day")),
            new FlashCard(new GermanPhrase("Dienstag"), new EnglishPhrase("Tuesday"),
                    getTagSet("day", "hard")),
            new FlashCard(new GermanPhrase("Mittwoch"), new EnglishPhrase("Wednesday"),
                    getTagSet("day", "veryHard")),
            new FlashCard(new GermanPhrase("Donnerstag"), new EnglishPhrase("Thursday"),
                    getTagSet("day")),
            new FlashCard(new GermanPhrase("Freitag"), new EnglishPhrase("Friday"),
                    getTagSet("day")),
            new FlashCard(new GermanPhrase("Samstag"), new EnglishPhrase("Saturday"),
                        getTagSet("day")),
            new FlashCard(new GermanPhrase("Sonntag"), new EnglishPhrase("Sunday"),
                        getTagSet("day"))
        };
    }

    public static ReadOnlyGlossary getSampleGlossary() {
        Glossary sampleG = new Glossary();
        for (FlashCard sampleFlashCard : getSampleFlashCards()) {
            sampleG.addFlashCard(sampleFlashCard);
        }
        return sampleG;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
