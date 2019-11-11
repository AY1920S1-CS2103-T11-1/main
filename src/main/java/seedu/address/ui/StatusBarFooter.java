package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;
    @FXML
    private Label sessionDetails;


    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        sessionDetails.setText("Status: Not Logged In");
    }

    //@@author madanalogy
    public void setLoginDetails(Person person, String time) {
        if (person != null) {
            assert(time != null);
            sessionDetails.setText("Status: Logged in as " + person.getUsername()
                    + " on " + time);
        } else {
            sessionDetails.setText("Status: Not Logged In");
        }
    }

}
