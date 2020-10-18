package seedu.forgetfulnus.model;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private Glossary glossary;
    private Glossary backupGlossary;
    private final UserPrefs userPrefs;
    private FilteredList<FlashCard> filteredFlashCards;

    private Predicate predicate = PREDICATE_SHOW_ALL_FLASHCARDS;

    private int quizModeIndex = 0;
    private boolean quizMode = false;
    private boolean isRandomQuiz = false;

    /**
     * Initializes a ModelManager with the given glossary and userPrefs.
     */
    public ModelManager(ReadOnlyGlossary glossary, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(glossary, userPrefs);

        logger.fine("Initialising with glossary: " + glossary + " and user prefs " + userPrefs);

        this.glossary = new Glossary(glossary);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashCards = new FilteredList<>(this.glossary.getFlashCardList());
    }

    public ModelManager() {
        this(new Glossary(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getGlossaryFilePath() {
        return userPrefs.getGlossaryFilePath();
    }

    @Override
    public void setGlossaryFilePath(Path glossaryFilePath) {
        requireNonNull(glossaryFilePath);
        userPrefs.setGlossaryFilePath(glossaryFilePath);
    }

    //=========== Glossary ================================================================================

    @Override
    public void setGlossary(ReadOnlyGlossary glossary) {
        this.glossary.resetData(glossary);
    }

    @Override
    public ReadOnlyGlossary getGlossary() {
        return glossary;
    }

    @Override
    public boolean hasFlashCard(FlashCard flashCard) {
        requireNonNull(flashCard);
        return glossary.hasFlashCard(flashCard);
    }

    @Override
    public void deleteFlashCard(FlashCard target) {
        glossary.removeFlashCard(target);
    }

    @Override
    public void addFlashCard(FlashCard flashCard) {
        glossary.addFlashCard(flashCard);
        updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireAllNonNull(target, editedFlashCard);

        glossary.setFlashCard(target, editedFlashCard);
    }

    //=========== Filtered FlashCard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code FlashCard} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<FlashCard> getFilteredFlashCardList() {
        return filteredFlashCards;
    }

    @Override
    public void updateFilteredPhraseList(Predicate<FlashCard> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        filteredFlashCards.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPhraseList() {
        requireAllNonNull(predicate);
        filteredFlashCards.setPredicate(predicate);
    }

    @Override
    public void updateQuizModeIndex(int index) {
        quizModeIndex = index;
    }

    @Override
    public int getQuizModeIndex() {
        return quizModeIndex;
    }

    @Override
    public void setQuizMode(boolean quizMode) {
        this.quizMode = quizMode;
    }

    @Override
    public boolean isQuizMode() {
        return quizMode;
    }

    @Override
    public void setRandomQuizMode(boolean isRandomQuiz) {
        this.isRandomQuiz = isRandomQuiz;
        if (isRandomQuiz) {
            backupGlossary = new Glossary(glossary);
        } else {
            setGlossary(new Glossary(backupGlossary));
            updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        }
    }

    @Override
    public boolean isRandomQuizMode() {
        return isRandomQuiz;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return glossary.equals(other.glossary)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashCards.equals(other.filteredFlashCards);
    }

}
