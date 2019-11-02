package seedu.address.model.vehicle;

import static java.util.Objects.requireNonNull;
// import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_VEHICLE_NUMBER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_VEHICLE_NUMBER;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's VehicleNumber in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVehicleNumber(String)}
 */
public class VehicleNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "All vehicle numbers must follow the format: ABC1234D, and must be unique.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX =
            "[\\p{Upper}][\\p{Upper}][\\p{Upper}][\\p{Digit}][\\p{Digit}][\\p{Digit}][\\p{Digit}][\\p{Upper}]";

    private static List<String> vehicleNumbers = new ArrayList<>();

    private final String vehicleNumber;

    /**
     * Constructs a {@code VehicleNumber}.
     *
     * @param number A valid VehicleNumber.
     */
    public VehicleNumber(String number) {
        requireNonNull(number);
        checkArgument(isValidVehicleNumber(number), MESSAGE_INVALID_VEHICLE_NUMBER);
        // checkArgument(isDuplicateVNum(number), MESSAGE_DUPLICATE_VEHICLE_NUMBER);
        vehicleNumber = number;
    }

    /**
     * Adds to list of unique vehicle numbers.
     * @param vNum
     */
    public void addVehicleNumber(String vNum) {
        if (!isDuplicateVNum(vNum)) {
            vehicleNumbers.add(vNum);
        }
    }
    /**
     * Checks if vehicle number already exsits.
     * @param vNum
     * @return
     */
    public static boolean isDuplicateVNum(String vNum) {
        if (vehicleNumbers.contains(vNum)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if a given string is a valid VehicleNumber.
     */
    public static boolean isValidVehicleNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return vehicleNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VehicleNumber // instanceof handles nulls
                && vehicleNumber.equals(((VehicleNumber) other).vehicleNumber)); // state check
    }

    @Override
    public int hashCode() {
        return vehicleNumber.hashCode();
    }

}

