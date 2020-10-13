package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.forgetfulnus.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.commons.util.CollectionUtil;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Edits the details of an existing flashcard in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited FlashCard: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the address book.";

    private final Index index;
    private final EditFlashCardDescriptor editFlashCardDescriptor;

    /**
     * @param index of the flashcard in the filtered flashcard list to edit
     * @param editFlashCardDescriptor details to edit the flashcard with
     */
    public EditCommand(Index index, EditFlashCardDescriptor editFlashCardDescriptor) {
        requireNonNull(index);
        requireNonNull(editFlashCardDescriptor);

        this.index = index;
        this.editFlashCardDescriptor = new EditFlashCardDescriptor(editFlashCardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard flashCardToEdit = lastShownList.get(index.getZeroBased());
        FlashCard editedFlashCard = createEditedFlashCard(flashCardToEdit, editFlashCardDescriptor);

        if (!flashCardToEdit.isSameFlashCard(editedFlashCard) && model.hasFlashCard(editedFlashCard)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.setFlashCard(flashCardToEdit, editedFlashCard);
        model.updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashCard));
    }

    /**
     * Creates and returns a {@code FlashCard} with the details of {@code flashCardToEdit}
     * edited with {@code editFlashCardDescriptor}.
     */
    private static FlashCard createEditedFlashCard(FlashCard flashCardToEdit,
                                                   EditFlashCardDescriptor editFlashCardDescriptor) {
        assert flashCardToEdit != null;

        GermanPhrase updatedGermanPhrase = editFlashCardDescriptor.getGermanPhrase()
                .orElse(flashCardToEdit.getGermanPhrase());
        EnglishPhrase updatedPhone = editFlashCardDescriptor
                .getEnglishPhrase()
                .orElse(flashCardToEdit.getEnglishPhrase());
        Set<Tag> updatedTags = editFlashCardDescriptor.getTags().orElse(flashCardToEdit.getTags());

        return new FlashCard(updatedGermanPhrase, updatedPhone, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFlashCardDescriptor.equals(e.editFlashCardDescriptor);
    }

    /**
     * Stores the details to edit the flashcard with. Each non-empty field value will replace the
     * corresponding field value of the flashcard.
     */
    public static class EditFlashCardDescriptor {
        private GermanPhrase germanPhrase;
        private EnglishPhrase englishPhrase;
        private Set<Tag> tags;

        public EditFlashCardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFlashCardDescriptor(EditFlashCardDescriptor toCopy) {
            setGermanPhrase(toCopy.germanPhrase);
            setEnglishPhrase(toCopy.englishPhrase);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(germanPhrase, englishPhrase, tags);
        }

        public void setGermanPhrase(GermanPhrase germanPhrase) {
            this.germanPhrase = germanPhrase;
        }

        public Optional<GermanPhrase> getGermanPhrase() {
            return Optional.ofNullable(germanPhrase);
        }

        public void setEnglishPhrase(EnglishPhrase englishPhrase) {
            this.englishPhrase = englishPhrase;
        }

        public Optional<EnglishPhrase> getEnglishPhrase() {
            return Optional.ofNullable(englishPhrase);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFlashCardDescriptor)) {
                return false;
            }

            // state check
            EditFlashCardDescriptor e = (EditFlashCardDescriptor) other;

            return getGermanPhrase().equals(e.getGermanPhrase())
                    && getEnglishPhrase().equals(e.getEnglishPhrase())
                    && getTags().equals(e.getTags());
        }
    }
}
