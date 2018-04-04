package guitests.guihandles;

import javafx.scene.layout.StackPane;

/**
 * Provides a handle for {@code RightPanel}.
 */
//@@author SuxianAlicia
public class RightPanelHandle extends NodeHandle<StackPane> {
    public static final String RIGHT_PANEL_ID = "rightPlaceholder";

    private final OrderListPanelHandle orderPanel;
    public final CalendarEntryListPanelHandle calendarEntryListPanel;


    protected RightPanelHandle(StackPane rootNode) {
        super(rootNode);

        orderPanel = new OrderListPanelHandle(getChildNode(OrderListPanelHandle.ORDER_LIST_VIEW_ID));
        calendarEntryListPanel = new CalendarEntryListPanelHandle(getChildNode(CalendarEntryListPanelHandle
                .CALENDAR_ENTRY_LIST_VIEW_ID));
    }

    public OrderListPanelHandle getOrderListPanelHandle() {
        return orderPanel;
    }

    public CalendarEntryListPanelHandle getCalendarEntryListPanel() {
        return calendarEntryListPanel;
    }
}
