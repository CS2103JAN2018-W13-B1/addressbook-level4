//@@author AJZ1995
package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Person;

/**
 *  Displays the contact details of a selected person
 */
public class PersonPanel extends UiPart<Region> {

    private static final String FXML = "PersonPanel.fxml";
    private static final double ICON_WIDTH = 25;
    private static final double ICON_HEIGHT = 25;

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private VBox panel;

    @FXML
    private Label name;

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

    public PersonPanel() {
        super(FXML);
        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void loadPersonPage(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().toString());
        address.setText(person.getAddress().toString());
        email.setText(person.getEmail().toString());
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

    @Subscribe
    public void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection().person);
    }
}
