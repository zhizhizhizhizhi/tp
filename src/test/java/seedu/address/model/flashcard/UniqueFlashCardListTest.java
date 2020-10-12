package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashCards.ALICE;
import static seedu.address.testutil.TypicalFlashCards.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.address.model.flashcard.exceptions.FlashCardNotFoundException;
import seedu.address.testutil.FlashCardBuilder;

public class UniqueFlashCardListTest {

    private final UniqueFlashCardList uniqueFlashCardList = new UniqueFlashCardList();

    @Test
    public void contains_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.contains(null));
    }

    @Test
    public void contains_flashCardNotInList_returnsFalse() {
        assertFalse(uniqueFlashCardList.contains(ALICE));
    }

    @Test
    public void contains_flashCardInList_returnsTrue() {
        uniqueFlashCardList.add(ALICE);
        assertTrue(uniqueFlashCardList.contains(ALICE));
    }

    @Test
    public void contains_flashCardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashCardList.add(ALICE);
        FlashCard editedAlice = new FlashCardBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueFlashCardList.contains(editedAlice));
    }

    @Test
    public void add_nullflashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.add(null));
    }

    @Test
    public void add_duplicateFlashCard_throwsDuplicateFlashCardException() {
        uniqueFlashCardList.add(ALICE);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList.add(ALICE));
    }

    @Test
    public void setFlashCard_nullTargetFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCard(null, ALICE));
    }

    @Test
    public void setFlashCard_nullEditedFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCard(ALICE, null));
    }

    @Test
    public void setFlashCard_targetFlashCardNotInList_throwsFlashCardNotFoundException() {
        assertThrows(FlashCardNotFoundException.class, () -> uniqueFlashCardList.setFlashCard(ALICE, ALICE));
    }

    @Test
    public void setFlashCard_editedFlashCardIsSameFlashCard_success() {
        uniqueFlashCardList.add(ALICE);
        uniqueFlashCardList.setFlashCard(ALICE, ALICE);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(ALICE);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasSameIdentity_success() {
        uniqueFlashCardList.add(ALICE);
        FlashCard editedAlice = new FlashCardBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueFlashCardList.setFlashCard(ALICE, editedAlice);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(editedAlice);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasDifferentIdentity_success() {
        uniqueFlashCardList.add(ALICE);
        uniqueFlashCardList.setFlashCard(ALICE, BOB);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(BOB);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasNonUniqueIdentity_throwsDuplicateFlashCardException() {
        uniqueFlashCardList.add(ALICE);
        uniqueFlashCardList.add(BOB);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList.setFlashCard(ALICE, BOB));
    }

    @Test
    public void remove_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.remove(null));
    }

    @Test
    public void remove_flashCardDoesNotExist_throwsFlashCardNotFoundException() {
        assertThrows(FlashCardNotFoundException.class, () -> uniqueFlashCardList.remove(ALICE));
    }

    @Test
    public void remove_existingFlashCard_removesFlashCard() {
        uniqueFlashCardList.add(ALICE);
        uniqueFlashCardList.remove(ALICE);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_nullUniqueFlashCardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCards((UniqueFlashCardList) null));
    }

    @Test
    public void setFlashCards_uniqueFlashCardList_replacesOwnListWithProvidedUniqueFlashCardList() {
        uniqueFlashCardList.add(ALICE);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(BOB);
        uniqueFlashCardList.setFlashCards(expectedUniqueFlashCardList);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCards((List<FlashCard>) null));
    }

    @Test
    public void setFlashCards_list_replacesOwnListWithProvidedList() {
        uniqueFlashCardList.add(ALICE);
        List<FlashCard> flashCardList = Collections.singletonList(BOB);
        uniqueFlashCardList.setFlashCards(flashCardList);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(BOB);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_listWithDuplicateFlashCards_throwsDuplicateFlashCardException() {
        List<FlashCard> listWithDuplicateFlashCards = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList
                .setFlashCards(listWithDuplicateFlashCards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashCardList.asUnmodifiableObservableList().remove(0));
    }
}
