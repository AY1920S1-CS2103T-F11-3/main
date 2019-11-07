package seedu.address.model.password.analyser.result;

import static java.util.Objects.requireNonNull;

import seedu.address.model.password.Password;

/**
 * Represents a result produced by an {@code Analyser} for a particular password.
 * Contains information about the description of the result, as well as the specific password.
 * Additionally it should be able to return greater details about the analysis performed by the {@code Analyser}.
 */
public abstract class Result {
    protected Password password;
    protected String description;
    protected String passwordDesc;
    protected String passwordUser;
    protected String passwordValue;

    /**
     * Constructs a basic {@code Result}
     *
     * @param password the specific password to which the result holds information about.
     * @param description the evaluation description of the {@code Analyser}.
     */
    public Result(Password password, String description) {
        requireNonNull(password);
        this.password = password;
        this.description = description;
        this.passwordDesc = password.getDescription().value;
        this.passwordUser = password.getUsername().value;
        this.passwordValue = password.getPasswordValue().getEncryptedPasswordValue();
    }

    public String getPasswordDesc() {
        return passwordDesc;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    /**
     * Sets the evaluation description of the Result.
     * @param description the evaluation description of the {@code Analyser}.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the evaluation description of the Result.
     * @return the description attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Provides further in-depth information about the result produced by the {@code Analyser}.
     * @return the specific details of the result of analysis in string format.
     */
    public abstract String getGreaterDetail();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result that = (Result) o;
        return password.equals(that.password);
    }

    /**
     * Provides summary information about the results produced by the {@code Analyser}.
     * @return brief detail of the result of analysis in string format.
     */
    @Override
    public String toString() {
        return String.format("%-20s %-5s %-20s %-5s %-20s %-5s %-20s", this.password.getPasswordDescription(),
                ":", this.password.getUsername(), ":",
                this.password.getPasswordValue() , ":" , getDescription()) + "\n";
    }

}
