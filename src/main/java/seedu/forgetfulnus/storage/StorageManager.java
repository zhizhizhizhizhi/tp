package seedu.forgetfulnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.ReadOnlyUserPrefs;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * Manages storage of Glossary data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GlossaryStorage glossaryStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code GlossaryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GlossaryStorage glossaryStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.glossaryStorage = glossaryStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Glossary methods ==============================

    @Override
    public Path getGlossaryFilePath() {
        return glossaryStorage.getGlossaryFilePath();
    }

    @Override
    public Optional<ReadOnlyGlossary> readGlossary() throws DataConversionException, IOException {
        return readGlossary(glossaryStorage.getGlossaryFilePath());
    }

    @Override
    public Optional<ReadOnlyGlossary> readGlossary(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return glossaryStorage.readGlossary(filePath);
    }

    @Override
    public void saveGlossary(ReadOnlyGlossary glossary) throws IOException {
        saveGlossary(glossary, glossaryStorage.getGlossaryFilePath());
    }

    @Override
    public void saveGlossary(ReadOnlyGlossary glossary, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        glossaryStorage.saveGlossary(glossary, filePath);
    }

}
