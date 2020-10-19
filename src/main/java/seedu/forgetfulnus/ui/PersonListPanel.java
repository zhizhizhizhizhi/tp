package seedu.forgetfulnus.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Panel containing the glossary.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "FlashCardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<FlashCard> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<FlashCard> flashCardList) {
        super(FXML);
        personListView.setItems(flashCardList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code FlashCard} using a {@code FlashCardCardView}.
     */
    class PersonListViewCell extends ListCell<FlashCard> {
        @Override
        protected void updateItem(FlashCard flashCard, boolean empty) {
            super.updateItem(flashCard, empty);

            if (empty || flashCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashCardCardView(flashCard, getIndex() + 1).getRoot());
            }
        }
    }

}
