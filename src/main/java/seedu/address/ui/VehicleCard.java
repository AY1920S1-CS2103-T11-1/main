package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.vehicle.Vehicle;

/**
 * An UI component that displays information of a {@code Vehicle}.
 */
public class VehicleCard extends UiPart<Region> {

    private static final String FXML = "VehicleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Vehicle vehicle;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label vehicleNumber;
    @FXML
    private Label vehicleType;
    @FXML
    private Label district;
    @FXML
    private Label availability;

    public VehicleCard(Vehicle vehicle, int displayedIndex) {
        super(FXML);
        this.vehicle = vehicle;
        id.setText(displayedIndex + ". ");
        vehicleNumber.setText(vehicle.getVehicleNumber().toString());
        vehicleType.setText(vehicle.getVehicleType().toString());
        district.setText("District: " + String.valueOf(vehicle.getDistrict().getDistrictNum()));
        availability.setText("Availability: " + vehicle.getAvailability().getAvailabilityTag());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VehicleCard)) {
            return false;
        }

        // state check
        VehicleCard card = (VehicleCard) other;
        return id.getText().equals(card.id.getText())
                && vehicle.equals(card.vehicle);
    }
}
