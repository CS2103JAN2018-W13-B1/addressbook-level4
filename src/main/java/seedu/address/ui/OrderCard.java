package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.ChangeOrderStatusEvent;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.order.Quantity;

/**
 * A UI component that displays information of an {@code Order}.
 */
public class OrderCard extends UiPart<Region> {
    private static final String FXML = "OrderListCard.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderCard.class);

    public final Order order;

    @FXML
    private HBox cardPane;

    @FXML
    private Label orderInformation;

    @FXML
    private Label orderStatus;

    @FXML
    private Label id;

    @FXML
    private Label priceAndQuantity;

    @FXML
    private Label totalPrice;

    @FXML
    private Label deliveryDate;

    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        orderInformation.setText(order.getOrderInformation().toString());
        orderStatus.setText(order.getOrderStatus().getCurrentOrderStatus().toUpperCase());
        priceAndQuantity.setText("S$" + order.getPrice().toString() + " X " + order.getQuantity().toString());
        totalPrice.setText("Total: S$" + getTotalPrice(order.getPrice(), order.getQuantity()));
        deliveryDate.setText("Deliver By: " + order.getDeliveryDate().toString());
    }

    private String getTotalPrice(Price price, Quantity quantity) {
        double priceValue = Double.valueOf(price.toString());
        int quantityValue = Integer.valueOf(quantity.toString());

        return String.valueOf(priceValue * quantityValue);
    }

    @FXML
    private void handleChangeOrderStatus(ChangeOrderStatusEvent event) {
        // TODO: dynamically assign style class to order depending on order status change
        orderStatus.setText(event.getOrderStatus().toUpperCase());
    }

    @Subscribe
    private void handleChangeOrderStatusEvent(ChangeOrderStatusEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleChangeOrderStatus(event);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
