package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyGlossary;

/**
 * A class to access Glossary data stored as a json file on the hard disk.
 */
public class JsonGlossaryStorage implements GlossaryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGlossaryStorage.class);

    private Path filePath;

    public JsonGlossaryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGlossaryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGlossary> readGlossary() throws DataConversionException {
        return readGlossary(filePath);
    }

    /**
     * Similar to {@link #readGlossary()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGlossary> readGlossary(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGlossary> jsonGlossary = JsonUtil.readJsonFile(
                filePath, JsonSerializableGlossary.class);
        if (!jsonGlossary.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGlossary.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGlossary(ReadOnlyGlossary glossary) throws IOException {
        saveGlossary(glossary, filePath);
    }

    /**
     * Similar to {@link #saveGlossary(ReadOnlyGlossary)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGlossary(ReadOnlyGlossary glossary, Path filePath) throws IOException {
        requireNonNull(glossary);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGlossary(glossary), filePath);
    }

}
