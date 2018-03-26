package seedu.address.ui;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.DisplayCalendarRequestEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;

public class CentrePanel extends UiPart<Region> {


    private static final String FXML = "CentrePanel.fxml";

    CalendarPanel calendarPanel;
    BrowserPanel browserPanel;

    @FXML
    private StackPane centrePlaceholder;

    public CentrePanel() {
        super(FXML);

        // By default, display Browser Panel in Main Window.
        displayBrowserPanel();
        registerAsAnEventHandler(this);
    }

    public void displayBrowserPanel() {
        browserPanel = new BrowserPanel();
        centrePlaceholder.getChildren().add(browserPanel.getRoot());
    }

    public void freeResources() {
        browserPanel.freeResources();
    }

    public void displayCalendarPanel() {
        calendarPanel = new CalendarPanel();
        centrePlaceholder.getChildren().add(calendarPanel.getRoot());
    }

    @Subscribe
    private void handleDisplayCalendarRequestEvent(DisplayCalendarRequestEvent event) {
        displayCalendarPanel();
        calendarPanel.handleDisplayCalendarRequestEvent(event);
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        displayBrowserPanel();
        browserPanel.handlePersonPanelSelectionChangedEvent(event);
    }
}
