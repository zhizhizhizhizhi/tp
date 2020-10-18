package seedu.forgetfulnus.logic.commands;

import javafx.collections.transformation.FilteredList;
import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static java.util.Objects.requireNonNull;

public class RandomQuizCommand extends Command {

    public static final String COMMAND_WORD = "random";

    public static final String MESSAGE_SUCCESS = "Random Quiz started!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Randomly selects specified number of flashcards and starts quiz\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String QUIZMODE_REMINDER = "Random Quiz has started. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private final Index targetIndex;

    public RandomQuizCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setRandomQuizMode(true);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (targetIndex.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        List<FlashCard> randomList = new ArrayList<>(lastShownList);
        Collections.shuffle(randomList);
        while (randomList.size() > targetIndex.getOneBased()) {
            randomList.remove(randomList.size() - 1);
        }
        Glossary randomGlossary = new Glossary();
        randomGlossary.setFlashCards(randomList);
        model.setGlossary(randomGlossary);
        model.updateFilteredPhraseList();
        ListIterator<FlashCard> iterator = model.getFilteredFlashCardList().listIterator();
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

    @Override
    public String getQuizModeReminder() {
        return QUIZMODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
