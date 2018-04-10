package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final double ICON_WIDTH = 25;
    private static final double ICON_HEIGHT = 25;

    public final Person person;

    @FXML
    private HBox cardPane;

    @FXML
    private Label name;

    @FXML
    private Label id;

    @FXML
    private Label phone;

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private FlowPane groups;

    @FXML
    private FlowPane preferences;

    @FXML
    private ImageView phoneIcon;

    @FXML
    private ImageView addressIcon;

    @FXML
    private ImageView emailIcon;

    @FXML
    private ImageView groupIcon;

    @FXML
    private ImageView prefIcon;


    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getGroupTags().forEach(group -> groups.getChildren().add(new Label(group.tagName)));
        person.getPreferenceTags().forEach(pref -> preferences.getChildren().add(new Label(pref.tagName)));
        setImageSizeForAllImages();
    }

    // @@author amad-person
    private void setImageSizeForAllImages() {
        phoneIcon.setFitWidth(ICON_WIDTH);
        phoneIcon.setFitHeight(ICON_HEIGHT);

        addressIcon.setFitWidth(ICON_WIDTH);
        addressIcon.setFitHeight(ICON_HEIGHT);

        emailIcon.setFitWidth(ICON_WIDTH);
        emailIcon.setFitHeight(ICON_HEIGHT);

        groupIcon.setFitWidth(ICON_WIDTH);
        groupIcon.setFitHeight(ICON_HEIGHT);

        prefIcon.setFitWidth(ICON_WIDTH);
        prefIcon.setFitHeight(ICON_HEIGHT);
    }
    // @@author

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
