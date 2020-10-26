package seedu.forgetfulnus.model.score;

import java.util.ArrayList;
import java.util.List;

import seedu.forgetfulnus.model.flashcard.FlashCard;

public class Score {

    private int score;
    private final int numQuestions;
    private final List<FlashCard> flashCards = new ArrayList<>();

    /**
     * Represents a Score from a round of quizzing.
     * Guarantees: details are present and not null.
     */
    public Score(int score, int numQuestions, List<FlashCard> testedFlashCards) {
        this.score = score;
        this.numQuestions = numQuestions;
        this.flashCards.addAll(testedFlashCards);
    }

    public int getScore() {
        return score;
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public List<FlashCard> getFlashcards() {
        return flashCards;
    }

    public void addFlashcard(FlashCard next) {
        flashCards.add(next);
    }

    public void incrementScore() {
        score++;
    }

    public int getIndex() {
        return this.flashCards.size();
    }

    public boolean isSameScore(Score editedScore) {
        return this.flashCards.equals(editedScore.flashCards);
    }
}
