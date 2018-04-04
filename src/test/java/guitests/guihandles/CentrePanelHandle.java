package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Provides a handle for {@code CentrePanel}.
 */
//@@author SuxianAlicia
public class CentrePanelHandle extends NodeHandle<StackPane> {
    public static final String CENTRE_PANEL_ID = "#centrePlaceholder";

    private final CalendarPanelHandle calendarPanel;
    public final PersonPanelHandle personPanel;

    protected CentrePanelHandle(StackPane rootNode) {
        super(rootNode);

        personPanel = new PersonPanelHandle(getChildNode(PersonPanelHandle.PERSON_PANEL_ID));
        calendarPanel = new CalendarPanelHandle(getChildNode(CalendarPanelHandle.CALENDAR_PANEL_ID));
    }

    public PersonPanelHandle getPersonPanelHandle() {
        return personPanel;
    }

    public CalendarPanelHandle getCalendarPanelHandle() {
        return calendarPanel;
    }
}
