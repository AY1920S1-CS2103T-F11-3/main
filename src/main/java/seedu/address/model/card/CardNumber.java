package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Cards's number in the card book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCardNumber(String)}
 */
public class CardNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Card number provided is not a valid VISA or MasterCard number.";

    public static final String VALIDATION_REGEX = "(4\\d{3}|5[1-5]\\d{2})-?(\\d{4})-?(\\d{4})-?(\\d{4})";

    public final String value;

    /**
     * Constructs a {@code CardNumber}.
     *
     * @param cardNumber A valid card number.
     */
    public CardNumber(String cardNumber) {
        requireNonNull(cardNumber);
        checkArgument(isValidCardNumber(cardNumber), MESSAGE_CONSTRAINTS);
        this.value = reformatCardNumber(cardNumber);
    }

    /**
     * Returns true if a given string is a valid card number.
     */
    public static boolean isValidCardNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private static String reformatCardNumber(String cardNumber) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(cardNumber);
        if (m.find()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= 4; i++) {
                sb.append(m.group(i));
                if (i < 4) {
                    sb.append("-");
                }
            }
            return sb.toString();
        }
        return null;
    }

    public String getEncryptedCardNumber() {
        return StringUtil.cardNumberToAsterix(value);
    }

    public String getNonEncryptedCardNumber() {
        return value;
    }

    @Override
    public String toString() {
        return getEncryptedCardNumber();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CardNumber // instanceof handles nulls
                && value.equals(((CardNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
