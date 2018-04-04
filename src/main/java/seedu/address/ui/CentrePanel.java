package seedu.address.ui;

import com.calendarfx.model.Calendar;
import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.events.ui.DisplayCalendarRequestEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;


/**
 * The Centre Panel of the App that can switch between Person Panel and Calendar Panel
 */
public class CentrePanel extends UiPart<Region> {

    private static final String FXML = "CentrePanel.fxml";

    private CalendarPanel calendarPanel;
<<<<<<< HEAD
    private BrowserPanel browserPanel;
=======
    private PersonPanel personPanel;
    private ObservableList<CalendarEvent> calendarEvents;
>>>>>>> upstream/master

    @FXML
    private StackPane centrePlaceholder;

    public CentrePanel(Calendar calendar) {
        super(FXML);

<<<<<<< HEAD
        browserPanel = new BrowserPanel();
        calendarPanel = new CalendarPanel(calendar);
        // By default, display Browser Panel in Main Window.
        displayBrowserPanel();
=======
        this.calendarEvents = calendarEvents;

        displayPersonPanel();
>>>>>>> upstream/master
        registerAsAnEventHandler(this);
    }

    /**
     * Displays the Person Panel.
     */
<<<<<<< HEAD
    public void displayBrowserPanel() {
        centrePlaceholder.getChildren().clear();
        centrePlaceholder.getChildren().add(browserPanel.getRoot());
=======
    public void displayPersonPanel() {
        personPanel = new PersonPanel();
        centrePlaceholder.getChildren().add(personPanel.getRoot());
>>>>>>> upstream/master
    }

    /**
     * Provides a method to access PersonPanel's method.
     */
    public void freeResources() {
        personPanel.freeResources();
    }

    /**
     * Displays the Calendar Panel.
     */
    public void displayCalendarPanel() {
        centrePlaceholder.getChildren().clear();
        centrePlaceholder.getChildren().add(calendarPanel.getRoot());
    }

    @Subscribe
    private void handleDisplayCalendarRequestEvent(DisplayCalendarRequestEvent event) {
<<<<<<< HEAD

=======
        calendarEvents = event.getCalendarEvents();
>>>>>>> upstream/master
        displayCalendarPanel();
        calendarPanel.handleDisplayCalendarRequestEvent(event);
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        displayPersonPanel();
        personPanel.handlePersonPanelSelectionChangedEvent(event);
    }
}
