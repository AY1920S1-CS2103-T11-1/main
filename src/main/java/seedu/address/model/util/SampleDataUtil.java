package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.IncidentManager;
import seedu.address.model.ReadOnlyIncidentManager;

import seedu.address.model.incident.CallerNumber;
import seedu.address.model.incident.Description;
import seedu.address.model.incident.Incident;
import seedu.address.model.incident.IncidentDateTime;
import seedu.address.model.incident.IncidentId;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Password;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Username;
import seedu.address.model.tag.Tag;
import seedu.address.model.vehicle.Availability;
import seedu.address.model.vehicle.District;
import seedu.address.model.vehicle.Vehicle;
import seedu.address.model.vehicle.VehicleNumber;
import seedu.address.model.vehicle.VehicleType;

/**
 * Contains utility methods for populating {@code IncidentManager} with sample data.
 */
public class SampleDataUtil {
    private static Person admin =
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
            getTagSet("Admin", "Team-1", "OC"), new Username("Agent01"), new Password("password"));

    public static Person getAdmin() {
        return admin;
    }

    private static Person[] getSamplePersons() {
        return new Person[] {
            admin,
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("Team-1"), new Username("Agent02"), new Password("password")),
            new Person(new Name("Ahmed Bahajjaj"), new Phone("92334716"), new Email("ahmed@example.com"),
                    getTagSet("Team-1"), new Username("Agent-11"), new Password("password")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("Team-2"), new Username("Operator01"), new Password("password")),
            new Person(new Name("Vivien Lim"), new Phone("93120238"), new Email("vivien@example.com"),
                    getTagSet("Team-2", "OC"), new Username("Operator-12"), new Password("password")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("Team-2"), new Username("Operator02"), new Password("password")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("Team-3"), new Username("Agent03"), new Password("password")),
            new Person(new Name("Ahmad Ibrahim"), new Phone("93647591"), new Email("ahmad@example.com"),
                    getTagSet("Team-3", "OC"), new Username("Agent-13"), new Password("password")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("Team-3", "Admin"), new Username("Operator03"), new Password("password")),
            new Person(new Name("Alex Lim"), new Phone("98984545"), new Email("alexlim@example.com"),
                getTagSet("Team-4"), new Username("Operator04"), new Password("password")),
            new Person(new Name("John Doe"), new Phone("87654321"), new Email("john@example.com"),
                    getTagSet("Team-4", "Intern"), new Username("Operator-14"), new Password("password")),
            new Person(new Name("Jane Tan"), new Phone("97856342"), new Email("alexlim@example.com"),
                    getTagSet("Team-4", "OC"), new Username("Operator04"), new Password("password")),
        };
    }

    private static Incident[] getSampleIncidents() {
        Person[] samplePersons = getSamplePersons();
        IncidentDateTime[] sampleIncidentDateTimes = getSampleIncidentDateTimes();
        Vehicle[] sampleVehicles = getSampleVehicles();

        Incident[] sampleIncidents = new Incident[] {
            new Incident(samplePersons[6], new District(2), sampleIncidentDateTimes[6],
                    new IncidentId(sampleIncidentDateTimes[6].getMonth(), sampleIncidentDateTimes[6].getYear()),
                    new CallerNumber("98984545"), new Description("Fight reported at District 2 intersection"),
                    Incident.Status.COMPLETE_DRAFT, sampleVehicles[5]),
            new Incident(samplePersons[0], new District(1), sampleIncidentDateTimes[0],
                new IncidentId(sampleIncidentDateTimes[0].getMonth(), sampleIncidentDateTimes[0].getYear()),
                new CallerNumber("93894576"), new Description("Shoplifting reported in District 1 Mall"),
                Incident.Status.SUBMITTED_REPORT, sampleVehicles[0]),
            new Incident(samplePersons[0], new District(2), sampleIncidentDateTimes[1],
                new IncidentId(sampleIncidentDateTimes[1].getMonth(), sampleIncidentDateTimes[1].getYear()),
                new CallerNumber("98098765"), new Description("PMD accident in District 2 park"),
                Incident.Status.COMPLETE_DRAFT, sampleVehicles[1]),
            new Incident(samplePersons[0], new District(3), sampleIncidentDateTimes[2],
                new IncidentId(sampleIncidentDateTimes[2].getMonth(), sampleIncidentDateTimes[2].getYear()),
                new CallerNumber("87595849"), new Description("Tiger spotted in District 3 courthouse."),
                Incident.Status.COMPLETE_DRAFT, sampleVehicles[2]),
            new Incident(samplePersons[3], new District(4), sampleIncidentDateTimes[3],
                new IncidentId(sampleIncidentDateTimes[3].getMonth(), sampleIncidentDateTimes[3].getYear()),
                new CallerNumber("89090908"), new Description("Minor traffic accident in District 4 highway"),
                Incident.Status.SUBMITTED_REPORT, sampleVehicles[3]),
            new Incident(samplePersons[4], new District(5), sampleIncidentDateTimes[4],
                new IncidentId(sampleIncidentDateTimes[4].getMonth(), sampleIncidentDateTimes[4].getYear()),
                new CallerNumber("87656743"), new Description("Arson reported at District 5 warehouse"),
                Incident.Status.COMPLETE_DRAFT, sampleVehicles[4]),
            new Incident(samplePersons[0], new District(6), sampleIncidentDateTimes[5],
                    new IncidentId(sampleIncidentDateTimes[5].getMonth(), sampleIncidentDateTimes[5].getYear()),
                    new CallerNumber("87656743"), new Description("Fire reported at District 6 warehouse"),
                    Incident.Status.COMPLETE_DRAFT, sampleVehicles[6])
        };

        return sampleIncidents;
    }

    private static IncidentDateTime[] getSampleIncidentDateTimes() {
        return new IncidentDateTime[] {
            new IncidentDateTime("2012-12-21T12:00:00"),
            new IncidentDateTime("2013-01-11T00:00:00"),
            new IncidentDateTime("2014-05-01T01:00:00"),
            new IncidentDateTime("2015-06-16T14:00:00"),
            new IncidentDateTime("2016-09-01T21:00:00"),
            new IncidentDateTime("2017-11-02T22:30:00"),
            new IncidentDateTime("2016-03-22T16:30:00")
        };
    }

    /**
     * The assigned vehicles are of "busy" status here because
     * it's just loading of data; they were alr dispatched.
     */
    public static Vehicle[] getSampleVehicles() {
        return new Vehicle[] {
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("SFD3204V"),
                    new District(1), new Availability("BUSY")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("SDF2044R"),
                    new District(2), new Availability("BUSY")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("FEP3249J"),
                    new District(3), new Availability("BUSY")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("GRB3294K"),
                    new District(4), new Availability("BUSY")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("GJR8923L"),
                    new District(5), new Availability("BUSY")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("SGS2121G"),
                new District(1), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("BBA2222F"),
                new District(6), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("FKH1221P"),
                new District(20), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("OLI4445C"),
                new District(8), new Availability("AVAILABLE")),

            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("SFD3204W"),
                    new District(1), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("SDG4044R"),
                    new District(2), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("FEG5249J"),
                    new District(3), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("GJE3294K"),
                    new District(4), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("GJN8923L"),
                    new District(5), new Availability("AVAILABLE")),

            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("SFD3264V"),
                    new District(6), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("SDF2344R"),
                    new District(7), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("FEP6249J"),
                    new District(8), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Ambulance"), new VehicleNumber("GRB3994K"),
                    new District(9), new Availability("AVAILABLE")),
            new Vehicle(new VehicleType("Patrol Car"), new VehicleNumber("GJR8123L"),
                    new District(10), new Availability("AVAILABLE"))

        };
    }

    public static ReadOnlyIncidentManager getSampleIncidentManager() {
        IncidentManager sampleAb = new IncidentManager();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Incident sampleIncident : getSampleIncidents()) {
            sampleAb.addIncident(sampleIncident);
        }
        for (Vehicle sampleVehicle : getSampleVehicles()) {
            sampleAb.addVehicle(sampleVehicle);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
