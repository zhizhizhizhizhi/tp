package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.flashcard.FlashCard;

import java.util.List;
import java.util.ListIterator;

public class QuizCommand extends Command {
    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "Quiz started!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        ListIterator<FlashCard> iterator = lastShownList.listIterator();
        while (iterator.hasNext()) {
            FlashCard toEdit = iterator.next();
            FlashCard changeTo = toEdit.copy();
            changeTo.updateShowingEnglish(false);
            model.setFlashCard(toEdit, changeTo);
        }
        model.setQuizMode(true);
        model.updateFilteredPhraseList();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
