package seedu.forgetfulnus.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.model.score.Score;
import seedu.forgetfulnus.model.score.UniqueScoreList;


public class ScoreList implements ReadOnlyScoreList {

    private final UniqueScoreList scores;

    public ScoreList() {
        scores = new UniqueScoreList();
    }

    /**
     * Wraps all score data.
     * Duplicates are not allowed (by .isSameScore comparison)
     */
    public ScoreList(ReadOnlyScoreList initialScores) {
        this();
        resetData(initialScores);
    }

    /**
     * Resets the existing data of this {@code ScoreList} with {@code scoreList}.
     */
    public void resetData(ReadOnlyScoreList scoreList) {
        requireNonNull(scoreList);
        setScores(scoreList.getScoreList());
    }

    private void setScores(List<Score> scoreList) {
        this.scores.setScores(scoreList);
    }

    @Override
    public ObservableList<Score> getScoreList() {
        return scores.asUnmodifiableObservableList();
    }

    public void addScore(Score score) {
        scores.add(score);
    }
}
