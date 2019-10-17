package seedu.address.model.password.analyser.match;

public class SequenceMatch extends BaseMatch {

    public SequenceMatch(int start_index, int end_index, String token) {
        super(start_index, end_index, token);
    }

    @Override
    public String toString() {
        return super.toString() + "Type : Sequence Match\n";
    }
}
