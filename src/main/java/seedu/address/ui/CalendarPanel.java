package seedu.address.ui;

import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.DisplayCalendarRequestEvent;

/**
 * Calendar Panel displaying calendar.
 * ContactSails implements CalendarFX to display Calendar.
 */
public class CalendarPanel extends UiPart<Region> {

    private static final String FXML = "CalendarPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private final CalendarView calendarView;

    private final CalendarSource calendarSource;

    @FXML
    private StackPane calendarPanelholder;

    public CalendarPanel(Calendar calendar) {
        super(FXML);
        calendarView = new CalendarView();
        calendarSource = new CalendarSource();

        initialiseCalendar(calendar);
        registerAsAnEventHandler(this);
    }

    /**
     * Sets up CalendarFX.
     */
    public void initialiseCalendar(Calendar calendar) {
        calendarSource.getCalendars().addAll(calendar);
        calendarView.getCalendarSources().setAll(calendarSource);
        calendarPanelholder.getChildren().setAll(calendarView);
    }


    @Subscribe
    public void handleDisplayCalendarRequestEvent(DisplayCalendarRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
    }

}
