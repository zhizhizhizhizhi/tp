package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.MONDAY;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TABLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.forgetfulnus.model.flashcard.exceptions.FlashCardNotFoundException;
import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class UniqueFlashCardListTest {

    private final UniqueFlashCardList uniqueFlashCardList = new UniqueFlashCardList();

    @Test
    public void contains_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.contains(null));
    }

    @Test
    public void contains_flashCardNotInList_returnsFalse() {
        assertFalse(uniqueFlashCardList.contains(MONDAY));
    }

    @Test
    public void contains_flashCardInList_returnsTrue() {
        uniqueFlashCardList.add(MONDAY);
        assertTrue(uniqueFlashCardList.contains(MONDAY));
    }

    @Test
    public void contains_flashCardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashCardList.add(MONDAY);
        FlashCard editedAlice = new FlashCardBuilder(MONDAY).withTags(VALID_TAG_CHAPTER_ONE)
                .build();
        assertTrue(uniqueFlashCardList.contains(editedAlice));
    }

    @Test
    public void add_nullflashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.add(null));
    }

    @Test
    public void add_duplicateFlashCard_throwsDuplicateFlashCardException() {
        uniqueFlashCardList.add(MONDAY);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList.add(MONDAY));
    }

    @Test
    public void setFlashCard_nullTargetFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCard(null, MONDAY));
    }

    @Test
    public void setFlashCard_nullEditedFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCard(MONDAY, null));
    }

    @Test
    public void setFlashCard_targetFlashCardNotInList_throwsFlashCardNotFoundException() {
        assertThrows(FlashCardNotFoundException.class, () -> uniqueFlashCardList.setFlashCard(MONDAY, MONDAY));
    }

    @Test
    public void setFlashCard_editedFlashCardIsSameFlashCard_success() {
        uniqueFlashCardList.add(MONDAY);
        uniqueFlashCardList.setFlashCard(MONDAY, MONDAY);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(MONDAY);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasSameIdentity_success() {
        uniqueFlashCardList.add(MONDAY);
        FlashCard editedAlice = new FlashCardBuilder(MONDAY).withTags(VALID_TAG_CHAPTER_ONE)
                .build();
        uniqueFlashCardList.setFlashCard(MONDAY, editedAlice);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(editedAlice);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasDifferentIdentity_success() {
        uniqueFlashCardList.add(MONDAY);
        uniqueFlashCardList.setFlashCard(MONDAY, TABLE);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(TABLE);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasNonUniqueIdentity_throwsDuplicateFlashCardException() {
        uniqueFlashCardList.add(MONDAY);
        uniqueFlashCardList.add(TABLE);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList.setFlashCard(MONDAY, TABLE));
    }

    @Test
    public void remove_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.remove(null));
    }

    @Test
    public void remove_flashCardDoesNotExist_throwsFlashCardNotFoundException() {
        assertThrows(FlashCardNotFoundException.class, () -> uniqueFlashCardList.remove(MONDAY));
    }

    @Test
    public void remove_existingFlashCard_removesFlashCard() {
        uniqueFlashCardList.add(MONDAY);
        uniqueFlashCardList.remove(MONDAY);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_nullUniqueFlashCardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCards((UniqueFlashCardList) null));
    }

    @Test
    public void setFlashCards_uniqueFlashCardList_replacesOwnListWithProvidedUniqueFlashCardList() {
        uniqueFlashCardList.add(MONDAY);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(TABLE);
        uniqueFlashCardList.setFlashCards(expectedUniqueFlashCardList);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCards((List<FlashCard>) null));
    }

    @Test
    public void setFlashCards_list_replacesOwnListWithProvidedList() {
        uniqueFlashCardList.add(MONDAY);
        List<FlashCard> flashCardList = Collections.singletonList(TABLE);
        uniqueFlashCardList.setFlashCards(flashCardList);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(TABLE);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_listWithDuplicateFlashCards_throwsDuplicateFlashCardException() {
        List<FlashCard> listWithDuplicateFlashCards = Arrays.asList(MONDAY, MONDAY);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList
                .setFlashCards(listWithDuplicateFlashCards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashCardList.asUnmodifiableObservableList().remove(0));
    }
}
