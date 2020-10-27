package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.ListIterator;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

public class QuizCommand extends Command {
    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "Quiz started!";
    public static final String QUIZ_MODE_REMINDER = "Quiz has started. "
            + "Enter 'end' to end quizzing.";
    public static final String NO_FLASHCARD_MESSAGE = "There is no flashcard to quiz with.\n "
            + "Add a flashcard with "
            + "'add g/<GERMAN PHRASE> e/<ENGLISH PHRASE> d/[<DIFFICULTY>] s/[<GENDER>] [t/<TAG>]}'";
    public static final String FIRST_CARD = " Enter the definition of: ";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        if (lastShownList.size() == 0) {
            return new CommandResult(NO_FLASHCARD_MESSAGE);
        }
        ListIterator<FlashCard> iterator = lastShownList.listIterator();
        while (iterator.hasNext()) {
            FlashCard toEdit = iterator.next();
            FlashCard changeTo = toEdit.copy();
            changeTo.updateShowingEnglish(false);
            model.setFlashCard(toEdit, changeTo);
        }
        model.updateFilteredPhraseList();
        model.setQuizMode(true);
        return new CommandResult(String.format(MESSAGE_SUCCESS) + FIRST_CARD
                + lastShownList.get(model.getQuizModeIndex()).getGermanPhrase().toString());
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
