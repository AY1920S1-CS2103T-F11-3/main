package seedu.address.model.password.analyser.match;

import seedu.address.model.password.Password;

public class UniqueMatch extends BaseMatch {
    private Password password;

    public UniqueMatch(int start_index, int end_index, String token, Password password) {
        super(start_index, end_index, token);
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + "Type : Unique Match\n" + "Account : " + this.password;
    }
}
