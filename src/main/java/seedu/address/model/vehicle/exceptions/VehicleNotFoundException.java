package seedu.address.model.vehicle.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException() {
        super("Vehicle not found in this district!");
    }
}
