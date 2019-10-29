package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's content in the content book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContent(String)}
 */
public class Content {

    public static final String MESSAGE_CONSTRAINTS = "Content can take any values, and it should not be blank";

    /*
     * The first character of the content must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].(?s).*";

    public final String content;

    /**
     * Constructs an {@code Content}.
     *
     * @param content A valid content.
     */
    public Content(String content) {
        requireNonNull(content);
//        checkArgument(isValidContent(content), MESSAGE_CONSTRAINTS);
        this.content = content;
    }

    /**
     * Returns true if a given string is a valid content.
     */
    public static boolean isValidContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Content // instanceof handles nulls
                && content.equals(((Content) other).content)); // state check
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

}
