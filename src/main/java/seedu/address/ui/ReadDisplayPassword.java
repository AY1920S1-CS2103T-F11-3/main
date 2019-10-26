package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.password.Password;

/**
 * A Ui for the displaying password that is displayed when read command is called.
 */
public class ReadDisplayPassword extends UiPart<Region> {
    private static final String FXML = "ReadDisplayPassword.fxml";

    @FXML
    private Label description;
    @FXML
    private TextArea username;
    @FXML
    private TextArea passwordValue;
    @FXML
    private FlowPane tags;
    @FXML
    private TextArea website;
    @FXML
    private Label lastModified;
    @FXML
    private Label lastAccessed;


    public ReadDisplayPassword() {
        super(FXML);
    }

    /**
     *
     * @param password
     */
    public void setFeedbackToUser(Password password) {
        requireNonNull(password);
        description.setText(password.getDescription().value);
        username.setText(password.getUsername().value);
        passwordValue.setText(password.getPasswordValue().toString());
        password.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
