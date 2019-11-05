package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ADMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.testutil.IncidentBuilder.DEFAULT_VEHICLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.IncidentManager;
import seedu.address.model.incident.CallerNumber;
import seedu.address.model.incident.Description;
import seedu.address.model.incident.Incident;
import seedu.address.model.incident.IncidentDateTime;
import seedu.address.model.incident.IncidentId;
import seedu.address.model.person.Person;
import seedu.address.model.vehicle.Availability;
import seedu.address.model.vehicle.District;
import seedu.address.model.vehicle.Vehicle;
import seedu.address.model.vehicle.VehicleNumber;
import seedu.address.model.vehicle.VehicleType;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalEntities {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withUsername("Opr1").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withUsername("Opr2").withPassword("password")
            .withTags(VALID_TAG_ADMIN, "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withUsername("Opr3").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withUsername("Opr4").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822224")
            .withEmail("werner@example.com").withUsername("Opr5").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withUsername("Opr6").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824421")
            .withEmail("anna@example.com").withUsername("Opr7").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824243")
            .withEmail("stefan@example.com").withUsername("Opr8").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821315")
            .withEmail("hans@example.com").withUsername("Opr9").withPassword("password")
            .withTags(VALID_TAG_ADMIN).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withUsername(VALID_USERNAME_AMY).withPassword(VALID_PASSWORD_AMY)
            .withTags(VALID_TAG_ADMIN).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withUsername(VALID_USERNAME_BOB).withPassword(VALID_PASSWORD_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_ADMIN)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private static final IncidentDateTime validDateTime = new IncidentDateTime("2016-12-02T14:30:40");
    private static final IncidentId commonIncidentId = new IncidentId("1220160001");
    private static final Incident COMMON_INCIDENT = new Incident(ALICE, new District(2), validDateTime,
            commonIncidentId, new CallerNumber("91234567"), new Description("test"),
            Incident.Status.SUBMITTED_REPORT, DEFAULT_VEHICLE);
    private TypicalEntities() {} // prevents instantiation

    /**
     * Returns an {@code IncidentManager} with all the typical incidents.
     */
    public static IncidentManager getTypicalIncidentManager() {
        IncidentManager im = new IncidentManager();

        for (Person person : getTypicalPersons()) {
            im.addPerson(person);
        }

        //add incidents
        Incident firstIncident = new Incident(ALICE, new District(2), validDateTime,
                commonIncidentId,
                new CallerNumber("84738293"),
                new Description("Pickpocket reported along the walkway in District 2"),
                Incident.Status.SUBMITTED_REPORT,
                new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("ABC1234D"),
                        new District(9), new Availability("BUSY")));
        im.addIncident(firstIncident);

        Incident secondIncident = new Incident(ALICE, new District(3), validDateTime,
                commonIncidentId,
                new CallerNumber("90878965"),
                new Description("Thief spotted"),
                Incident.Status.SUBMITTED_REPORT,
                new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("GBC1434D"),
                        new District(4), new Availability("BUSY")));

        im.addIncident(secondIncident);

        //add vehicles
        im.addVehicle(new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("SBH3100F"),
                new District(16), new Availability("BUSY")));

        im.addVehicle(new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("GER4389F"),
                new District(4), new Availability("AVAILABLE")));



        return im;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
