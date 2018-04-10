//@@author AJZ1995
package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.ResetPersonPanelEvent;
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
        loadBlankPersonPage();
    }

    /**
     * Loads a blank page when no contact is selected.
     */
    private void loadBlankPersonPage() {
        name.setText("");
        phone.setText("");
        address.setText("");
        email.setText("");
        groups.getChildren().clear();
        preferences.getChildren().clear();
        initBlankIcons();
    }

    //@@author amad-person
    /**
     * Sets all image icons to blank.
     */
    private void initBlankIcons() {
        phoneIcon.setImage(null);
        addressIcon.setImage(null);
        emailIcon.setImage(null);
        prefIcon.setImage(null);
        groupIcon.setImage(null);
    }
    //@@author

    @Subscribe
    private void loadPersonPage(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().toString());
        address.setText(person.getAddress().toString());
        email.setText(person.getEmail().toString());
        person.getGroupTags().forEach(group -> groups.getChildren().add(new Label(group.tagName)));
        person.getPreferenceTags().forEach(pref -> preferences.getChildren().add(new Label(pref.tagName)));
        setIcons();
        setImageSizeForAllImages();
    }

    private void setIcons() {
        Image phoneIconImage = new Image("images/phone_icon.png");
        phoneIcon.setImage(phoneIconImage);

        Image addressIconImage = new Image("images/address_icon.png");
        addressIcon.setImage(addressIconImage);

        Image emailIconImage = new Image("images/email_icon.png");
        emailIcon.setImage(emailIconImage);

        Image prefIconImage = new Image("images/pref_icon.png");
        prefIcon.setImage(prefIconImage);

        Image groupIconImage = new Image("images/group_icon.png");
        groupIcon.setImage(groupIconImage);
    }

    //@@author amad-person
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
    //@@author

    @Subscribe
    public void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        loadBlankPersonPage();
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection().person);
    }

    @Subscribe
    private void handlePersonPanelNoSelectionEvent(ResetPersonPanelEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadBlankPersonPage();
    }
}
