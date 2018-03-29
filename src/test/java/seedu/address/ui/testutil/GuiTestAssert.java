package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.OrderCardHandle;
import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {

    private static final String LABEL_DEFAULT_STYLE = "label";
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(PersonCardHandle expectedCard, PersonCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getPhone(), actualCard.getPhone());
        assertEquals(expectedCard.getGroups(), actualCard.getGroups());
        assertEquals(expectedCard.getPreferences(), actualCard.getPreferences());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysPerson(Person expectedPerson, PersonCardHandle actualCard) {
        assertEquals(expectedPerson.getName().fullName, actualCard.getName());
        assertEquals(expectedPerson.getPhone().value, actualCard.getPhone());
        assertEquals(expectedPerson.getEmail().value, actualCard.getEmail());
        assertEquals(expectedPerson.getAddress().value, actualCard.getAddress());
        assertEquals(expectedPerson.getGroupTags().stream().map(group -> group.tagName).collect(Collectors.toList()),
                actualCard.getGroups());
        assertEquals(expectedPerson.getPreferenceTags().stream().map(pref -> pref.tagName).collect(Collectors.toList()),
                actualCard.getPreferences());
    }

    /*
     * Code adopted from PR CS2103T Appendix A UI component
     */
    /* Returns the color style for {@code tagName}'s label. The tag's color is determined by
     * looking up the color in {@code PersonCard#TAG_COLOUR_STYLES}, using an index generated
     * by the hash code of the tag's content
     * @see PersonCard#getTagColourStyleFor(String)
     */
    private static String getTagColourStyleFor(String tagName) {
        switch(tagName) {
        case "young":
            return "red";

        case "teenager":
            return "orange";

        case "female adult":
            return "yellow";

        case "male adult":
            return "green";

        case "old lady":
            return "blue";

        case "old man":
            return "violet";

        case "baby":
            return "purple";

        default:
            fail(tagName + "does not have a colour assigned");
            return "";
        }
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedOrder}.
     */
    public static void assertCardDisplaysOrder(Order expectedOrder, OrderCardHandle actualCard) {
        assertEquals(expectedOrder.getOrderInformation().toString(), actualCard.getOrderInformation());

        String expectedPriceAndQuantity = "S$" + expectedOrder.getPrice().toString() + " X "
                + expectedOrder.getQuantity().toString();
        assertEquals(expectedPriceAndQuantity, actualCard.getPriceAndQuantity());

        String expectedTotalPrice = "Total: S$" + String.valueOf(
                Double.parseDouble(expectedOrder.getPrice().toString())
                        * Integer.parseInt(expectedOrder.getQuantity().toString()));

        assertEquals(expectedTotalPrice, actualCard.getTotalPrice());

        assertEquals("Deliver By: " + expectedOrder.getDeliveryDate().toString(), actualCard.getDeliveryDate());
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, Person... persons) {
        for (int i = 0; i < persons.length; i++) {
            assertCardDisplaysPerson(persons[i], personListPanelHandle.getPersonCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, List<Person> persons) {
        assertListMatching(personListPanelHandle, persons.toArray(new Person[0]));
    }

    /**
     * Asserts the size of the list in {@code personListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(PersonListPanelHandle personListPanelHandle, int size) {
        int numberOfPeople = personListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
