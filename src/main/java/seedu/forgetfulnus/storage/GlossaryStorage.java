package seedu.forgetfulnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.model.ReadOnlyGlossary;

/**
 * Represents a storage for {@link seedu.forgetfulnus.model.Glossary}.
 */
public interface GlossaryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGlossaryFilePath();

    /**
     * Returns Glossary data as a {@link ReadOnlyGlossary}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGlossary> readGlossary() throws DataConversionException, IOException;

    /**
     * @see #getGlossaryFilePath()
     */
    Optional<ReadOnlyGlossary> readGlossary(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyGlossary} to the storage.
     * @param glossary cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGlossary(ReadOnlyGlossary glossary) throws IOException;

    /**
     * @see #saveGlossary(ReadOnlyGlossary)
     */
    void saveGlossary(ReadOnlyGlossary glossary, Path filePath) throws IOException;

}
