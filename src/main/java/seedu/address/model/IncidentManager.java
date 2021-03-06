package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.incident.Incident;
import seedu.address.model.incident.UniqueIncidentList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.vehicle.UniqueVehicleList;
import seedu.address.model.vehicle.Vehicle;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class IncidentManager implements ReadOnlyIncidentManager {

    private final UniquePersonList persons;
    private final UniqueIncidentList incidents;
    private final UniqueVehicleList vehicles;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        incidents = new UniqueIncidentList();
        vehicles = new UniqueVehicleList();
    }

    public IncidentManager() {}

    /**
     * Creates an IncidentManager using the Persons and Vehicles in the {@code toBeCopied}
     */
    public IncidentManager(ReadOnlyIncidentManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the incident list with {@code incidents}.
     * {@code incidents} must not contain duplicate incident.
     */
    public void setIncidents(List<Incident> incidents) {
        this.incidents.setIncidents(incidents);
    }

    /**
     * Replaces the contents of the vehicle list with {@code vehicles}.
     * {@code vehicles} must not contain duplicate vehicles.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles.setVehicles(vehicles);
    }

    /**
     * Resets the existing data of this {@code IncidentManager} with {@code newData}.
     */
    public void resetData(ReadOnlyIncidentManager newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setIncidents(newData.getIncidentList());
        setVehicles(newData.getVehicleList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    public void setIncident(Incident target, Incident editedIncident) {
        requireNonNull(editedIncident);
        incidents.setIncident(target, editedIncident);
    }

    /**
     * Removes {@code key} from this {@code IncidentManager}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// incident-level operation

    /**
     * Returns true if an incident with the same identity as {@code incident} exists in the incident manager.
     */
    public boolean hasIncident(Incident incident) {
        requireNonNull(incident);
        return incidents.contains(incident);
    }

    /**
     * Adds an incident to the front of the list in incident manager.
     * The incident must not already exist in the incident manager.
     */
    public void addIncident(Incident i) {
        incidents.add(i);
    }

    /**
     * Adds an incident to the back of the list in incident manager.
     * The incident must not already exist in the incident manager.
     */
    public void appendIncident(Incident i) {
        incidents.append(i);
    }

    /**
     * Removes an incident to the incident manager.
     * The incident must not already exist in the incident manager.
     */
    public void removeIncident(Incident i) {
        incidents.remove(i);
    }

    //// vehicle-level operation

    /**
     * Returns true if a vehicle with the same identity as {@code vehicle} exists in the address book.
     */
    public boolean hasVehicle(Vehicle vehicle) {
        requireNonNull(vehicle);
        return vehicles.contains(vehicle);
    }

    /**
     * Returns true if a vehicle with the same vehicle number exists in the IMS.
     * @param vNum the vehicle number to be compared.
     */
    public boolean hasVehicleNumber(String vNum) {
        requireNonNull(vNum);
        return vehicles.containsVNum(vNum);
    }

    /**
     * Adds a vehicle to the address book.
     * The vehicle must not already exist in the address book.
     */
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }


    public void setVehicle(Vehicle target, Vehicle editedVehicle) {
        requireNonNull(editedVehicle);
        vehicles.setVehicle(target, editedVehicle);
    }

    /**
     * Deletes {@code Vehicle toDelete} from the incident manager provided it exists.
     */
    public void deleteVehicle(Vehicle toDelete) {
        requireNonNull(toDelete);
        vehicles.remove(toDelete);

    }
    //// util methods
    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons" + "\n"
                + incidents.asUnmodifiableObservableList().size() + " incidents" + "\n"
                + vehicles.asUnmodifiableObservableList().size() + " vehicles";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Incident> getIncidentList() {
        return incidents.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Vehicle> getVehicleList() {
        return vehicles.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IncidentManager // instanceof handles nulls
                && persons.equals(((IncidentManager) other).persons)
                && incidents.equals(((IncidentManager) other).incidents)
                && vehicles.equals(((IncidentManager) other).vehicles));

    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
