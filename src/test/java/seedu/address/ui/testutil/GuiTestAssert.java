package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonCard;

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
        assertEquals(expectedCard.getTags(), actualCard.getTags());

        expectedCard.getTags().forEach(tag -> assertEquals(expectedCard.getTagStyleClasses(tag), actualCard.getTagStyleClasses(tag)));
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysPerson(Person expectedPerson, PersonCardHandle actualCard) {
        assertEquals(expectedPerson.getName().fullName, actualCard.getName());
        assertTagsEqual(expectedPerson, actualCard);
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

    /*
     * Asserts that the tags in {@code actualCard} matches all the tags in {@code expectedPerson}
     * with the correct colour.
     */

    private static void assertTagsEqual(Person expectedPerson, PersonCardHandle actualCard) {
        List<String> expectedTags = expectedPerson.getTags().stream().map(tag -> tag.tagName)
                .collect(Collectors.toList());

        assertEquals(expectedTags, actualCard.getTags());
        expectedTags.forEach(tag ->
                assertEquals(Arrays.asList(LABEL_DEFAULT_STYLE, getTagColourStyleFor(tag)),
                        actualCard.getTagStyleClasses(tag)));

        assertEquals(expectedPerson.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList()),
                actualCard.getTags());
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
