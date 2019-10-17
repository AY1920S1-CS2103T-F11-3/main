package seedu.address.model.password.analyser.match;

public class Match implements Comparable<Match> {

    private String token;
    private int start_index;
    private int end_index;
    private int rank;

    public Match(int start_index, int end_index, String token, int rank) {
        this.token = token;
        this.start_index = start_index;
        this.end_index = end_index;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Token: " + this.token + "\n"
                + "Start Index: " + this.start_index + "\n"
                + "End Index: " + this.end_index + "\n";
    }


    @Override
    public int compareTo(Match o) {
        return this.rank - o.rank;
    }
}
