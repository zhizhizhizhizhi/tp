package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashCards.ALICE;
import static seedu.address.testutil.TypicalFlashCards.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.flashcard.FlashCard;
import seedu.address.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.address.testutil.FlashCardBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getFlashCardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateFlashCards_throwsDuplicateFlashCardException() {
        // Two flashCards with the same identity fields
        FlashCard editedAlice = new FlashCardBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<FlashCard> newFlashCards = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newFlashCards);

        assertThrows(DuplicateFlashCardException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasFlashCard_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasFlashCard(null));
    }

    @Test
    public void hasFlashCard_flashCardNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasFlashCard(ALICE));
    }

    @Test
    public void hasFlashCard_flashCardInAddressBook_returnsTrue() {
        addressBook.addFlashCard(ALICE);
        assertTrue(addressBook.hasFlashCard(ALICE));
    }

    @Test
    public void hasFlashCard_flashCardWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addFlashCard(ALICE);
        FlashCard editedAlice = new FlashCardBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasFlashCard(editedAlice));
    }

    @Test
    public void getFlashCardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFlashCardList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose flashCards list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<FlashCard> flashCards = FXCollections.observableArrayList();

        AddressBookStub(Collection<FlashCard> flashCards) {
            this.flashCards.setAll(flashCards);
        }

        @Override
        public ObservableList<FlashCard> getFlashCardList() {
            return flashCards;
        }
    }

}
