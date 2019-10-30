package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.vehicle.Availability;
import seedu.address.model.vehicle.District;
import seedu.address.model.vehicle.Vehicle;
import seedu.address.model.vehicle.VehicleNumber;
import seedu.address.model.vehicle.VehicleType;

/**
 * Jackson-friendly version of {@link Vehicle}.
 */
class JsonAdaptedVehicle {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Vehicle's %s field is missing!";

    private final String vehicleType;
    private final String vehicleNumber;
    private final int districtNum;
    private final String availability;

    /**
     * Constructs a {@code JsonAdaptedVehicle} with the given vehicle details.
     */
    @JsonCreator
    public JsonAdaptedVehicle(@JsonProperty("vehicleType") String vehicleType,
                              @JsonProperty("vehicleNumber") String vehicleNumber,
                              @JsonProperty("district") int districtNum,
                              @JsonProperty("availability") String availability) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.districtNum = districtNum;
        this.availability = availability;
    }

    /**
     * Converts a given {@code Vehicle} into this class for Jackson use.
     */
    public JsonAdaptedVehicle(Vehicle source) {
        vehicleType = source.getVehicleType().toString();
        vehicleNumber = source.getVehicleNumber().toString();
        districtNum = source.getDistrict().getDistrictNum();
        availability = source.getAvailability().getAvailabilityTag();
    }

    /**
     * Converts this Jackson-friendly adapted vehicle object into the model's {@code Vehicle} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted vehicle.
     */
    public Vehicle toModelType() throws IllegalValueException {
        if (vehicleNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    VehicleNumber.class.getSimpleName()));
        }
        if (!VehicleNumber.isValidVehicleNumber(vehicleNumber)) {
            throw new IllegalValueException(VehicleNumber.MESSAGE_CONSTRAINTS);
        }
        final VehicleNumber modelVehicleNumber = new VehicleNumber(vehicleNumber);

        if (vehicleType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    VehicleType.class.getSimpleName()));
        }
        if (!VehicleType.isValidVehicleType(vehicleType)) {
            throw new IllegalValueException(VehicleType.MESSAGE_CONSTRAINTS);
        }
        final VehicleType modelVehicleType = new VehicleType(vehicleType);

        if (districtNum == 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    District.class.getSimpleName()));
        }
        if (!District.isValidDistrict(districtNum)) {
            throw new IllegalValueException(District.MESSAGE_CONSTRAINTS);
        }
        final District modelDistrict = new District(districtNum);

        if (availability == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Availability.class.getSimpleName()));
        }
        if (!Availability.isValidAvailability(availability)) {
            throw new IllegalValueException(Availability.MESSAGE_CONSTRAINTS);
        }
        final Availability modelAvailability = new Availability(availability);

        return new Vehicle(modelVehicleType, modelVehicleNumber, modelDistrict, modelAvailability);
    }

}
