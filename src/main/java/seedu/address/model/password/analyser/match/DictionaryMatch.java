package seedu.address.model.password.analyser.match;

public class DictionaryMatch extends BaseMatch implements Comparable<DictionaryMatch> {
    private int rank;

    public DictionaryMatch(int start_index, int end_index, String token, int rank) {
        super(start_index, end_index, token);
        this.rank = rank;
    }

    @Override
    public int compareTo(DictionaryMatch o) {
        return this.rank - o.rank;
    }

    @Override
    public String toString() {
        return super.toString() + "Type : Dictionary Match\n" + "Rank : " + this.rank + "\n";
    }
}
