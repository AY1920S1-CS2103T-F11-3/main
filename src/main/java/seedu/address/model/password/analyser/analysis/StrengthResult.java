package seedu.address.model.password.analyser.analysis;

import seedu.address.model.password.Password;

public class StrengthResult extends BaseResult {

    boolean hasMinimumLength = false;
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasNum = false;
    boolean hasSpecial = false;

    public StrengthResult(Password password, String description, boolean hasMinimumLength, boolean hasLower, boolean hasUpper, boolean hasNum, boolean hasSpecial) {
        super(password, description);
        this.hasMinimumLength = hasMinimumLength;
        this.hasLower = hasLower;
        this.hasUpper = hasUpper;
        this.hasNum = hasNum;
        this.hasSpecial = hasSpecial;
    }

    @Override
    public String getGreaterDetail() {
        StringBuilder report = new StringBuilder("Result : " + description + "\n");
        if (!hasMinimumLength) {
            report.append("[-] Try to have at least 8 character for password.\n");
        }
        if (!hasLower || !hasUpper) {
            report.append("[-] Try to include a mix a both upper and lower case letters.\n");
        }
        if (!hasNum) {
            report.append("[-] Try to include a numerals.\n");
        }
        if (!hasSpecial) {
            report.append("[-] Try to include a special characters.\n");
        }
        if (hasMinimumLength && hasLower && hasUpper && hasNum && hasSpecial) {
            report.append("Password is strong.\n");
        }
        return report.toString();
    }
}
