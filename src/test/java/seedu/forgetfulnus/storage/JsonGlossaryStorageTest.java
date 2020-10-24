package seedu.forgetfulnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.MONDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.MORNING;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.NOON;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.ReadOnlyGlossary;

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
    public void readGlossary_invalidFlashCardGlossary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGlossary("invalidFlashCardGlossary.json"));
    }

    @Test
    public void readGlossary_invalidAndValidPersonGlossary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGlossary("invalidAndValidFlashCardGlossary.json"));
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
        original.addFlashCard(MORNING);
        original.removeFlashCard(MONDAY);
        jsonGlossaryStorage.saveGlossary(original, filePath);
        readBack = jsonGlossaryStorage.readGlossary(filePath).get();
        assertEquals(original, new Glossary(readBack));

        // Save and read without specifying file path
        original.addFlashCard(NOON);
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
