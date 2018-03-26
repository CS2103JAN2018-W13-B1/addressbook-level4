package seedu.address.ui;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.DisplayCalendarRequestEvent;

/**
 * Calendar Panel displaying calendar in Month-View Format.
 * The code is adapted from: https://github.com/sedj601/CalendarFx
 */
public class CalendarPanel extends UiPart<Region> {

    private static final String FXML = "CalendarPanel.fxml";
    private static final String MONTH = "Month";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private Label month;

    @FXML
    private Label year;

    @FXML
    private GridPane daysOfMonth;

    public CalendarPanel() {
        super(FXML);

        initialiseCalendar(LocalDateTime.now());
        registerAsAnEventHandler(this);
    }

    public CalendarPanel(LocalDateTime currentLdt) {
        super(FXML);

        initialiseCalendar(currentLdt);
        registerAsAnEventHandler(this);
    }

    private void initialiseCalendar(LocalDateTime givenLdt) {
        LocalDateTime currentLdt = givenLdt;
        loadCurrentMonth(currentLdt);

    }

    /**
     * Sets Month and Year Labels to display Month and Year corresponding to given {@code LocalDateTime}.
     * Loads the number of daysOfMonth and any events set in the given month of the year.
     */
    private void loadCurrentMonth(LocalDateTime givenLdt) {

        if (daysOfMonth.getChildren().size() > 0) {
            daysOfMonth.getChildren().clear();
        }

        month.setText(givenLdt.getMonth().toString());
        year.setText(Integer.toString(givenLdt.getYear()));

        setDaysOfWeek();

        //The following sets the date numbers in gridpane for the current Month and Year.
        LocalDateTime ldtIterator = givenLdt.minusDays(givenLdt.getDayOfMonth() - 1);
        ldtIterator.format(DateTimeFormatter.ISO_DATE);

        int control = getColumn(ldtIterator);
        int control2 = 0;
        int i = 0;
        while(ldtIterator.getMonth() == givenLdt.getMonth())
        {
            if( i == 0 || i == 1 && control2 <= control)
            {
                i = 1;
                control2++;
            }
            else
            {
                i = ((control2 - (control + 1)) / 7) + 2;
                control2++;
            }

            Label tempLabel = new Label(Integer.toString(ldtIterator.getDayOfMonth()));


            daysOfMonth.add(createCell(tempLabel, ldtIterator), ldtIterator.getDayOfWeek().getValue() - 1, i);

            ldtIterator = ldtIterator.plusDays(1);
        }

    }

    /**
     * Sets first row of daysOfMonth to be the 7 days of the week, starting from Monday.
     */
    private void setDaysOfWeek() {

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(days[i]);
            GridPane.setHalignment(dayLabel, HPos.CENTER);
            daysOfMonth.add(dayLabel, i, 0);
        }
    }

    /**
     * Returns the starting column number in GridPane based on givenLdt.
     * @param givenLdt
     * @return
     */
    private int getColumn (LocalDateTime givenLdt) {

        int i = 0;
        while(givenLdt.getDayOfWeek() != DayOfWeek.SUNDAY) {
            i++;
            givenLdt = givenLdt.plusDays(1);
        }

        return i;
    }

    private BorderPane createCell(Label label, LocalDateTime ldt) {

        BorderPane cell = new BorderPane();

        VBox vbox = new VBox();
        //vbox.getChildren().addAll(loadNotesForDate(ldt)); To be implemented later when Events are ready
        BorderPane.setAlignment(vbox, Pos.CENTER);
        cell.setCenter(vbox);

        BorderPane.setAlignment(label, Pos.TOP_RIGHT);
        cell.setTop(label);
        cell.getStyleClass().add("cell");

        return cell;
    }

    public void handleDisplayCalendarRequestEvent(DisplayCalendarRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
    }

}
