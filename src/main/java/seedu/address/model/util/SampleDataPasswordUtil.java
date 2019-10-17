package seedu.address.model.util;

import seedu.address.model.PasswordBook;
import seedu.address.model.password.Description;
import seedu.address.model.password.Password;
import seedu.address.model.password.PasswordValue;
import seedu.address.model.password.Username;

/**
 * Contains utility methods for populating {@code PasswordBook} with sample data.
 */
public class SampleDataPasswordUtil {
    public static Password[] getSamplePasswords() {
        return new Password[] {
            new Password(new Description("Gmail"), new Username("Randomguy"),
                        new PasswordValue("qwerty123")),
            new Password(new Description("Gmail1"), new Username("Randomguy1"),
                        new PasswordValue("password1456")),
            new Password(new Description("Gmail2"), new Username("Randomguy2"),
                        new PasswordValue("password1ABC")),
            new Password(new Description("Gmail3"), new Username("Randomguy3"),
                        new PasswordValue("password3123ABC")),
            new Password(new Description("Gmail4"), new Username("Randomguy4"),
                        new PasswordValue("password4"))
        };
    }

    public static PasswordBook getSamplePasswordBook() {
        PasswordBook samplePb = new PasswordBook();
        for (Password samplePassword : getSamplePasswords()) {
            samplePb.addPassword(samplePassword);
        }
        return samplePb;
    }
}
