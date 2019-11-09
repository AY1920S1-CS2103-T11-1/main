package seedu.address.model.vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.vehicle.exceptions.DuplicateVehicleException;
import seedu.address.model.vehicle.exceptions.VehicleNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of Vehicles that enforces uniqueness between its elements and does not allow nulls.
 * A Vehicle is considered unique by comparing using {@code Vehicle#isSameVehicle(Vehicle)}.
 * As such, adding and updating of Vehicles uses Vehicle#isSameVehicle(Vehicle) for equality,
 * so as to ensure that the Vehicle being added or updated is unique
 * in terms of identity in the UniqueVehicleList.
 * However, the removal of a Vehicle uses Vehicle#equals(Object)
 * so as to ensure that the Vehicle with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueVehicleList implements Iterable<Vehicle> {

    private final ObservableList<Vehicle> internalList = FXCollections.observableArrayList();
    private final ObservableList<Vehicle> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Vehicle as the given argument.
     */
    public boolean contains(Vehicle toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Checks if a vehicle of the same vehicle number exists in the unique vehicle list.
     * @param vNum is the vehicle number to be compared.
     */
    public boolean containsVNum(String vNum) {
        requireNonNull(vNum);
        boolean result = false;
        for (Vehicle v: internalList) {
            if (v.getVehicleNumber().toString().equalsIgnoreCase((vNum))) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Adds a Vehicle to the list.
     * The Vehicle must not already exist in the list.
     */
    public void add(Vehicle toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateVehicleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Vehicle {@code target} in the list with {@code editedVehicle}.
     * {@code target} must exist in the list.
     * The Vehicle identity of {@code editedVehicle} must not be the same as another existing Vehicle in the list.
     */
    public void setVehicle(Vehicle target, Vehicle editedVehicle) {
        requireAllNonNull(target, editedVehicle);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new VehicleNotFoundException();
        }

        if (!target.equals(editedVehicle) && contains(editedVehicle)) {
            throw new DuplicateVehicleException();
        }

        internalList.set(index, editedVehicle);
    }

    /**
     * Removes the equivalent Vehicle from the list.
     * The Vehicle must exist in the list.
     */
    public void remove(Vehicle toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new VehicleNotFoundException();
        }
    }

    public void setVehicles(UniqueVehicleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Vehicles}.
     * {@code Vehicles} must not contain duplicate Vehicles.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        requireAllNonNull(vehicles);
        if (!vehiclesAreUnique(vehicles)) {
            throw new DuplicateVehicleException();
        }

        internalList.setAll(vehicles);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Vehicle> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Vehicle> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueVehicleList // instanceof handles nulls
                && internalList.equals(((UniqueVehicleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Vehicles} contains only unique Vehicles.
     */
    private boolean vehiclesAreUnique(List<Vehicle> vehicles) {
        for (int i = 0; i < vehicles.size() - 1; i++) {
            for (int j = i + 1; j < vehicles.size(); j++) {
                if (vehicles.get(i).equals(vehicles.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

