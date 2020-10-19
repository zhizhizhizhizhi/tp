package seedu.forgetfulnus.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * An UI component that displays information of a {@code FlashCard}.
 */
public class FlashCardCardView extends UiPart<Region> {

    private static final String FXML = "FlashCardListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final FlashCard flashCard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label germanPhrase;
    @FXML
    private Label id;
    @FXML
    private Label englishPhrase;
    @FXML
    private FlowPane difficultyTagEasy;
    @FXML
    private FlowPane difficultyTagMedium;
    @FXML
    private FlowPane difficultyTagHard;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code FlashCard} and index to display.
     */
    public FlashCardCardView(FlashCard flashCard, int displayedIndex) {
        super(FXML);
        this.flashCard = flashCard;
        id.setText(displayedIndex + ". ");
        germanPhrase.setText(flashCard.getGermanPhrase().fullGermanPhrase);
        englishPhrase.setText(flashCard.isShowingEnglish() ? flashCard.getEnglishPhrase().fullEnglishPhrase : "");
        if (flashCard.getDifficultyTag().tagName.equals("EASY")) {
            difficultyTagEasy.getChildren().add(new Label(flashCard.getDifficultyTag().tagName));
        } else if (flashCard.getDifficultyTag().tagName.equals("MEDIUM")) {
            difficultyTagMedium.getChildren().add(new Label(flashCard.getDifficultyTag().tagName));
        } else if (flashCard.getDifficultyTag().tagName.equals("HARD")) {
            difficultyTagHard.getChildren().add(new Label(flashCard.getDifficultyTag().tagName));
        }
        flashCard.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashCardCardView)) {
            return false;
        }

        // state check
        FlashCardCardView card = (FlashCardCardView) other;
        return id.getText().equals(card.id.getText())
                && flashCard.equals(card.flashCard);
    }
}
