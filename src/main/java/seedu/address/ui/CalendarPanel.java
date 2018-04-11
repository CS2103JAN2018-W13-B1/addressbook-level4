package seedu.address.ui;
//@@author SuxianAlicia
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.ChangeCalendarDateRequestEvent;
import seedu.address.commons.events.ui.ChangeCalendarPageRequestEvent;
import seedu.address.commons.events.ui.DisplayCalendarRequestEvent;
import seedu.address.ui.util.CalendarFxUtil;

/**
 * Calendar Panel displaying calendar.
 * ContactSails implements CalendarFX to display Calendar.
 */
public class CalendarPanel extends UiPart<Region> {

    private static final String DAY_VIEW = "Day";
    private static final String MONTH_VIEW = "Month";
    private static final String WEEK_VIEW = "Week";

    private static final String REQUEST_TODAY = "Today";
    private static final String REQUEST_BACK = "Back";
    private static final String REQUEST_NEXT = "Next";

    private static final String FXML = "CalendarPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private final CalendarView calendarView;
    private final CalendarSource calendarSource;

    @FXML
    private StackPane calendarPanelholder;

    public CalendarPanel(Calendar calendar) {
        super(FXML);
        calendarView = CalendarFxUtil.returnModifiedCalendarView();
        calendarSource = new CalendarSource();

        initialiseCalendar(calendar);
        createTimeThread();
    }

    /**
     * Adapted from CalendarFX developer manual QuickStart section.
     * http://dlsc.com/wp-content/html/calendarfx/manual.html#_quick_start
     */
    private void createTimeThread() {
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    /**
     * Sets up CalendarFX.
     */
    public void initialiseCalendar(Calendar calendar) {
        calendarSource.getCalendars().addAll(calendar);
        calendarView.getCalendarSources().setAll(calendarSource);
        calendarPanelholder.getChildren().setAll(calendarView);
    }

    /**
     * Handles Request to display Calendar in specific viewing format.
     */
    public void handleDisplayCalendarRequestEvent(DisplayCalendarRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        String view = event.getView();
        if (view != null) {
            if (view.equalsIgnoreCase(MONTH_VIEW)) {
                calendarView.showMonthPage();
            } else if (view.equalsIgnoreCase(DAY_VIEW)) {
                calendarView.showDayPage();
            } else if (view.equalsIgnoreCase(WEEK_VIEW)) {
                calendarView.showWeekPage();
            }
        }
    }

    /**
     * Handles request to change the current page of the Calendar to the page in {@code event}.
     */
    public void handleChangeCalendarPageRequestEvent(ChangeCalendarPageRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        String request = event.getRequestType();
        if (request.equals(REQUEST_TODAY)) {
            calendarView.getSelectedPage().goToday();
        } else if (request.equals(REQUEST_BACK)) {
            calendarView.getSelectedPage().goBack();
        } else if (request.equals(REQUEST_NEXT)) {
            calendarView.getSelectedPage().goForward();
        }
    }

    /**
     * Handles request to change the current date of the Calendar to the date in {@code event}.
     */
    public void handleChangeCalendarDateRequestEvent(ChangeCalendarDateRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        calendarView.setDate(event.getDate());
    }
}
