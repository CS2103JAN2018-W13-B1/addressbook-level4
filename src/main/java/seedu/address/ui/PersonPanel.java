//@@author AJZ1995
package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
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
    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private Person selectedPerson;

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
    }

    /*
    * Loads a person page
    */
    private void loadPersonPage() {
        name.setText(selectedPerson.getName().fullName);
        phone.setText(selectedPerson.getPhone().toString());
        address.setText(selectedPerson.getAddress().toString());
        email.setText(selectedPerson.getEmail().toString());
        selectedPerson.getGroupTags().forEach(group -> groups.getChildren().add(new Label(group.tagName)));
        selectedPerson.getPreferenceTags().forEach(pref -> preferences.getChildren().add(new Label(pref.tagName)));
    }

    @Subscribe
    public void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        loadBlankPersonPage();
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        selectedPerson = event.getNewSelection().person;
        loadPersonPage();
    }

    @Subscribe
    private void handlePersonPanelNoSelectionEvent(ResetPersonPanelEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadBlankPersonPage();
    }
}
