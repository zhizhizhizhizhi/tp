package seedu.forgetfulnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.MONDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TUESDAY;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.model.flashcard.GermanPhraseContainsKeywordsPredicate;
import seedu.forgetfulnus.testutil.GlossaryBuilder;

public class ModelManagerTest {

    private Glossary initialData = new Glossary();
    private ScoreList initialScores = new ScoreList();
    private UserPrefs userPrefs = new UserPrefs();
    private ModelManager modelManager = new ModelManager(initialData, initialScores, userPrefs);

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Glossary(), new Glossary(modelManager.getGlossary()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGlossaryFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setGlossaryFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setGlossaryPath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGlossaryFilePath(null));
    }

    @Test
    public void setGlossaryFilePath_validPath_setsGlossaryFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setGlossaryFilePath(path);
        assertEquals(path, modelManager.getGlossaryFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFlashCard(null));
    }

    @Test
    public void hasPerson_personNotInGlossary_returnsFalse() {
        assertFalse(modelManager.hasFlashCard(MONDAY));
    }

    @Test
    public void hasPerson_personInGlossary_returnsTrue() {
        modelManager.addFlashCard(MONDAY);
        assertTrue(modelManager.hasFlashCard(MONDAY));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFlashCardList().remove(0));
    }

    @Test
    public void equals() {
        Glossary glossary = new GlossaryBuilder().withFlashCard(MONDAY).withFlashCard(TUESDAY).build();
        Glossary differentGlossary = new Glossary();
        ScoreList scoreList = new ScoreList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(glossary, scoreList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(glossary, scoreList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentGlossary, scoreList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = MONDAY.getGermanPhrase().toString().split("\\s+");
        modelManager.updateFilteredPhraseList(new GermanPhraseContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(glossary, scoreList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setGlossaryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(glossary, scoreList, differentUserPrefs)));
    }
}
