package seedu.forgetfulnus.logic.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the glossary according to the input parameter.\n"
            + "Possible parameters: german, english, easy, hard\n"
            + "Example: " + COMMAND_WORD + " german";

    public static final String MESSAGE_SORT_SUCCESS = "The glossary has been sorted!";
    public static final Comparator<FlashCard> GERMAN_COMP = (obj1, obj2) -> obj1.getGermanPhrase().toString()
            .compareTo(obj2.getGermanPhrase().toString());
    public static final Comparator<FlashCard> REVERSE_GERMAN_COMP = (obj1, obj2) -> obj2.getGermanPhrase().toString()
            .compareTo(obj1.getGermanPhrase().toString());
    public static final Comparator<FlashCard> ENGLISH_COMP = (obj1, obj2) -> obj1.getEnglishPhrase().toString()
            .compareTo(obj2.getEnglishPhrase().toString());
    public static final Comparator<FlashCard> REVERSE_ENGLISH_COMP = (obj1, obj2) -> obj2.getEnglishPhrase().toString()
            .compareTo(obj1.getEnglishPhrase().toString());
    public static final Comparator<FlashCard> DIFFICULTY_EASY_COMP = (obj1, obj2) -> obj1.getDifficultyTag()
            .compareTo(obj2.getDifficultyTag());
    public static final Comparator<FlashCard> DIFFICULTY_HARD_COMP = (obj1, obj2) -> obj2.getDifficultyTag()
            .compareTo(obj1.getDifficultyTag());
    private Comparator<FlashCard> comp;

    /**
     * Creates a new SortCommand using the input parameter from the user.
     * @param parameter the parameter that the user wants to sort the glossary by.
     */
    public SortCommand(String parameter) {
        assert parameter != null : "Input cannot be null!";
        parameter = parameter.trim().toLowerCase();
        switch(parameter) {
        case("german"):
            comp = GERMAN_COMP;
            break;
        case("english"):
            comp = ENGLISH_COMP;
            break;
        case("reversegerman"):
            comp = REVERSE_GERMAN_COMP;
            break;
        case("reverseenglish"):
            comp = REVERSE_ENGLISH_COMP;
            break;
        case("easytohard"):
            comp = DIFFICULTY_EASY_COMP;
            break;
        case("hardtoeasy"):
            comp = DIFFICULTY_HARD_COMP;
            break;
        default:
            assert false : "Invalid parameter %s";
        }
    }
    @Override
    public CommandResult execute(Model model) {
        ListCommand.setOriginalGlossary(new Glossary(model.getGlossary()));
        List<FlashCard> sortedList = new ArrayList<>(model.getGlossary().getFlashCardList());
        sortedList.sort(comp);
        Glossary glossary = new Glossary();
        glossary.setFlashCards(sortedList);
        model.setGlossary(glossary);
        return new CommandResult(MESSAGE_SORT_SUCCESS);
    }
    @Override
    public CommandType isQuizModeCommand() {
        return CommandType.ANY_MODE;
    }
    @Override
    public String getQuizModeReminder() {
        return "";
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        SortCommand otherCommand = (SortCommand) other;
        return this.comp.equals(otherCommand.comp);
    }
}
