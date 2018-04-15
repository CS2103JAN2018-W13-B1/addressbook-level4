package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.CalendarManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCalendarManager;
import seedu.address.model.order.DeliveryDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderInformation;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.Quantity;
import seedu.address.model.order.exceptions.DuplicateOrderException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Group;
import seedu.address.model.tag.Preference;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getGroupSet("friends", "twitter"), getPreferenceSet("videogames")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getGroupSet("colleagues", "friends", "facebook"), getPreferenceSet("cosmetics", "shoes")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getGroupSet("neighbours", "facebook"), getPreferenceSet("skirts")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getGroupSet("family", "instagram"), getPreferenceSet("shoes")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getGroupSet("neighbours", "instagram"), getPreferenceSet("videoGames", "computers")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getGroupSet("colleagues", "facebook"), getPreferenceSet("photobooks", "notebooks")),
            new Person(new Name("Cory Kendall"), new Phone("98736281"), new Email("coryk@example.com"),
                new Address("Blk 10 Geylang Street 54, #10-11"),
                getGroupSet("colleagues", "twitter"), getPreferenceSet("computers", "stationery")),
            new Person(new Name("Harmon Ong"), new Phone("80550990"), new Email("ongharmon@example.com"),
                new Address("25 Pandan Valley Hill, #06-25"),
                getGroupSet("facebook", "colleagues"), getPreferenceSet("boardgames", "toys")),
            new Person(new Name("Serena Vasoo"), new Phone("90939326"), new Email("serena@example.com"),
                new Address("Blk 119 Jurong West Street 16, #05-15"),
                getGroupSet("facebook", "twitter"), getPreferenceSet("cosmetics", "toys")),
            new Person(new Name("Desiree Toh"), new Phone("97853771"), new Email("dtoh@example.com"),
                new Address("Blk 10 Lorong 3 Mandai, #10-19"),
                getGroupSet("instagram", "friends"), getPreferenceSet("skirts", "shirts")),
            new Person(new Name("Pete Lim"), new Phone("91748826"), new Email("petelim@example.com"),
                new Address("Blk 15 Lorong 6 Cairnhill, #16-39"),
                getGroupSet("colleagues", "twitter"), getPreferenceSet("boardgames", "notebooks")),
            new Person(new Name("Rahimah Mohamad"), new Phone("92241726"), new Email("rahimahm@example.com"),
                new Address("4 Bedok Reservoir Green"),
                getGroupSet("neighbours", "instagram"), getPreferenceSet("cosmetics", "computers")),
            new Person(new Name("Heather Kwok"), new Phone("87241809"), new Email("kwokheather@example.com"),
                new Address("Blk 184 Woodlands Junction"),
                getGroupSet("friends", "twitter"), getPreferenceSet("smartphones")),
            new Person(new Name("Wallace Yeoh"), new Phone("90204440"), new Email("wallaceiscool@example.com"),
                new Address("80 Kembangan Avenue"),
                getGroupSet("facebook", "twitter"), getPreferenceSet("toys", "shirts")),
            new Person(new Name("Mathias Yap"), new Phone("82335441"), new Email("mathiasyap@example.com"),
                new Address("Blk 131 Farrer Gate"),
                getGroupSet("instagram", "neighbours"), getPreferenceSet("photobooks", "shirts")),
            new Person(new Name("Eunice Thompson"), new Phone("97874468"), new Email("thompsoneunice@example.com"),
                new Address("8 Jurong Court, #10-34"),
                getGroupSet("instagram"), getPreferenceSet("shoes", "smartphones")),
            new Person(new Name("Janice Ho"), new Phone("82975816"), new Email("janiceho@example.com"),
                new Address("89 Serangoon North Circle"),
                getGroupSet("instagram"), getPreferenceSet( "smartphones")),
            new Person(new Name("Jerald Hong"), new Phone("89027276"), new Email("jeraldhong@example.com"),
                new Address("Blk 240 Tampines Street 13, #04-08"),
                getGroupSet("facebook", "friends"), getPreferenceSet("stationery")),
            new Person(new Name("Sammy Zhen"), new Phone("89270028"), new Email("sammyzenmode@example.com"),
                new Address("45 Bedok Reservoir Park, #09-17"),
                getGroupSet("facebook", "colleagues"), getPreferenceSet("videogames")),
            new Person(new Name("Emma Choo"), new Phone("99247028"), new Email("emmachoochoo@example.com"),
                new Address("3 Joo Koon Junction, #14-40"),
                getGroupSet("twitter"), getPreferenceSet("cosmetics", "toys"))
        };
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(
                new OrderInformation("Books"), new OrderStatus("ongoing"), new Price("12.50"),
                new Quantity("3"), new DeliveryDate("12-08-2018")
            ),
            new Order(
                new OrderInformation("Facewash"), new OrderStatus("done"), new Price("15.00"),
                new Quantity("2"), new DeliveryDate("05-11-2018")
            ),
            new Order(
                new OrderInformation("Chocolates"), new OrderStatus("ongoing"), new Price("5.00"),
                new Quantity("10"), new DeliveryDate("22-04-2018")
            ),
            new Order(
                new OrderInformation("Shoes"), new OrderStatus("ongoing"), new Price("129.99"),
                new Quantity("1"), new DeliveryDate("10-05-2018")
            ),
            new Order(
                new OrderInformation("Blue Shirt"), new OrderStatus("done"), new Price("20.99"),
                new Quantity("1"), new DeliveryDate("05-03-2018")
            ),
            new Order(
                new OrderInformation("Makeup Remover"), new OrderStatus("ongoing"), new Price("15.50"),
                new Quantity("5"), new DeliveryDate("29-04-2018")
            ),
            new Order(
                new OrderInformation("Monopoly"), new OrderStatus("ongoing"), new Price("45.00"),
                new Quantity("2"), new DeliveryDate("28-04-2018")
            ),
            new Order(
                new OrderInformation("Computer"), new OrderStatus("done"), new Price("1500.00"),
                new Quantity("1"), new DeliveryDate("15-06-2018")
            ),
            new Order(
                new OrderInformation("LED TV"), new OrderStatus("ongoing"), new Price("2999.99"),
                new Quantity("2"), new DeliveryDate("21-08-2018")
            ),
            new Order(
                new OrderInformation("Injustice 2"), new OrderStatus("ongoing"), new Price("200.00"),
                new Quantity("2"), new DeliveryDate("11-09-2018")
            ),
            new Order(
                new OrderInformation("Batman Plushie"), new OrderStatus("done"), new Price("17.99"),
                new Quantity("5"), new DeliveryDate("19-03-2018")
            ),
            new Order(
                new OrderInformation("Skirts"), new OrderStatus("ongoing"), new Price("15.50"),
                new Quantity("10"), new DeliveryDate("16-08-2018")
            ),
            new Order(
                new OrderInformation("Mints"), new OrderStatus("ongoing"), new Price("2.50"),
                new Quantity("20"), new DeliveryDate("17-06-2018")
            ),
            new Order(
                new OrderInformation("Formal Shirts"), new OrderStatus("ongoing"), new Price("39.99"),
                new Quantity("5"), new DeliveryDate("10-10-2018")
            ),
            new Order(
                new OrderInformation("Running Shoes"), new OrderStatus("done"), new Price("359.99"),
                new Quantity("1"), new DeliveryDate("12-03-2018")
            ),
            new Order(
                new OrderInformation("Sweatshirts"), new OrderStatus("done"), new Price("25.00"),
                new Quantity("3"), new DeliveryDate("25-11-2018")
            ),
            new Order(
                new OrderInformation("Smartphone"), new OrderStatus("done"), new Price("750.00"),
                new Quantity("1"), new DeliveryDate("13-10-2018")
            ),
            new Order(
                new OrderInformation("Notebooks"), new OrderStatus("ongoing"), new Price("3.50"),
                new Quantity("20"), new DeliveryDate("22-12-2018")
            ),
            new Order(
                new OrderInformation("Towels"), new OrderStatus("ongoing"), new Price("10.00"),
                new Quantity("6"), new DeliveryDate("22-11-2018")
            ),
            new Order(
                new OrderInformation("Coffee Mugs"), new OrderStatus("done"), new Price("12.00"),
                new Quantity("6"), new DeliveryDate("19-05-2018")
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAb = new AddressBook();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }

            for (Order sampleOrder : getSampleOrders()) {
                sampleAb.addOrderToOrderList(sampleOrder);
            }
            return sampleAb;
        } catch (DuplicatePersonException dpe) {
            throw new AssertionError("sample data cannot contain duplicate persons", dpe);
        } catch (DuplicateOrderException doe) {
            throw new AssertionError("sample data cannot contain duplicate orders", doe);
        }
    }

    /**
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        HashSet<Group> groupTags = new HashSet<>();
        for (String s : strings) {
            groupTags.add(new Group(s));
        }

        return groupTags;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Preference> getPreferenceSet(String... strings) {
        HashSet<Preference> prefTags = new HashSet<>();
        for (String s : strings) {
            prefTags.add(new Preference(s));
        }

        return prefTags;
    }

    //@@author SuxianAlicia
    /**
     * Returns a {@code CalendarManager} with no {@code CalendarEntry} in it.
     */
    public static ReadOnlyCalendarManager getSampleCalendarManager() {
        CalendarManager sampleCm = new CalendarManager();
        return sampleCm;
    }
    //@@author
}
