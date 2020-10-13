package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashCards.ALICE;
import static seedu.address.testutil.TypicalFlashCards.HOON;
import static seedu.address.testutil.TypicalFlashCards.IDA;
import static seedu.address.testutil.TypicalFlashCards.getTypicalGlossary;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Glossary;
import seedu.address.model.ReadOnlyGlossary;

public class JsonGlossaryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonGlossaryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGlossary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGlossary(null));
    }

    private java.util.Optional<ReadOnlyGlossary> readGlossary(String filePath) throws Exception {
        return new JsonGlossaryStorage(Paths.get(filePath)).readGlossary(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGlossary("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readGlossary("notJsonFormatGlossary.json"));
    }

    @Test
    public void readGlossary_invalidPersonGlossary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGlossary("invalidPersonGlossary.json"));
    }

    @Test
    public void readGlossary_invalidAndValidPersonGlossary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGlossary("invalidAndValidPersonGlossary.json"));
    }

    @Test
    public void readAndSaveGlossary_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGlossary.json");
        Glossary original = getTypicalGlossary();
        JsonGlossaryStorage jsonGlossaryStorage = new JsonGlossaryStorage(filePath);

        // Save in new file and read back
        jsonGlossaryStorage.saveGlossary(original, filePath);
        ReadOnlyGlossary readBack = jsonGlossaryStorage.readGlossary(filePath).get();
        assertEquals(original, new Glossary(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonGlossaryStorage.saveGlossary(original, filePath);
        readBack = jsonGlossaryStorage.readGlossary(filePath).get();
        assertEquals(original, new Glossary(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonGlossaryStorage.saveGlossary(original); // file path not specified
        readBack = jsonGlossaryStorage.readGlossary().get(); // file path not specified
        assertEquals(original, new Glossary(readBack));

    }

    @Test
    public void saveGlossary_nullGlossary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGlossary(null, "SomeFile.json"));
    }

    /**
     * Saves {@code glossary} at the specified {@code filePath}.
     */
    private void saveGlossary(ReadOnlyGlossary glossary, String filePath) {
        try {
            new JsonGlossaryStorage(Paths.get(filePath))
                    .saveGlossary(glossary, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGlossary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGlossary(new Glossary(), null));
    }
}
