package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Deletes a flashcard identified using it's displayed index from the glossary.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the flashcard identified by the index number used in the glossary.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FLASHCARD_SUCCESS = "Deleted Phrase: %1$s";

    public static final String QUIZMODE_REMINDER = "Flashcards cannot be deleted in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Adjusts the {@code Orders}s of all the FlashCards whose {@code Orders}s are greater than the FlashCard
     * removed. This fills up the gap that will remain after a FlashCard is deleted.
     * @param model the current model.
     * @param deletedFlashCardOrder the Order of the FlashCard to be deleted.
     */
    public void shiftOrders(Model model, int deletedFlashCardOrder) {
        Glossary copiedGlossary = new Glossary(model.getGlossary());
        ObservableList<FlashCard> immutableList = model.getGlossary().getFlashCardList();
        for (FlashCard flashCard : immutableList) {
            int currentOrder = flashCard.getOrder().getValue(); // old order of the flashcard
            if (currentOrder > deletedFlashCardOrder) {
                FlashCard newFc = flashCard.setOrder(currentOrder - 1); // copies the flashcard with the new order
                copiedGlossary.setFlashCard(flashCard, newFc); // replaces the old flashcard with the new one
            }
        }
        model.setGlossary(copiedGlossary);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard phraseToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteFlashCard(phraseToDelete);
        shiftOrders(model, phraseToDelete.getOrder().getValue());
        int size = model.getGlossary().getFlashCardList().size();
        AddCommand.setNextOrderOfAddition(size + 1);
        return new CommandResult(String.format(MESSAGE_DELETE_FLASHCARD_SUCCESS, phraseToDelete));
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZMODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
