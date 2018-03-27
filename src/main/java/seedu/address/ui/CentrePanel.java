package seedu.address.ui;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.events.ui.DisplayCalendarRequestEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;

/**
 * The Centre Panel of the App that can switch between Browser Panel and Calendar Panel
 */
public class CentrePanel extends UiPart<Region> {


    private static final String FXML = "CentrePanel.fxml";

    private CalendarPanel calendarPanel;
    private BrowserPanel browserPanel;

    @FXML
    private StackPane centrePlaceholder;

    public CentrePanel() {
        super(FXML);

        // By default, display Browser Panel in Main Window.
        displayBrowserPanel();
        registerAsAnEventHandler(this);
    }

    /**
     * Displays the Browser Panel.
     */
    public void displayBrowserPanel() {
        browserPanel = new BrowserPanel();
        centrePlaceholder.getChildren().add(browserPanel.getRoot());
    }

    /**
     * Provides a method to access BrowserPanel's method.
     */
    public void freeResources() {
        browserPanel.freeResources();
    }

    /**
     * Displays the Calendar Panel.
     */
    public void displayCalendarPanel() {
        calendarPanel = new CalendarPanel();
        centrePlaceholder.getChildren().add(calendarPanel.getRoot());
    }

    @Subscribe
    private void handleDisplayCalendarRequestEvent(DisplayCalendarRequestEvent event) {
        centrePlaceholder.getChildren().clear();
        displayCalendarPanel();
        calendarPanel.handleDisplayCalendarRequestEvent(event);
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        centrePlaceholder.getChildren().clear();
        displayBrowserPanel();
        browserPanel.handlePersonPanelSelectionChangedEvent(event);
    }
}
