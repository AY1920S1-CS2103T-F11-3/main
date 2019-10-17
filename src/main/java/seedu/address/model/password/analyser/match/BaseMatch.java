package seedu.address.model.password.analyser.match;

public abstract class BaseMatch implements Match{

    private String token;
    private int start_index;
    private int end_index;
    //private int rank;

    public BaseMatch(int start_index, int end_index, String token) {
        this.token = token;
        this.start_index = start_index;
        this.end_index = end_index;
        //this.rank = rank; TODO : transfer
    }

    public String getToken() {
        return token;
    }

    public int getStart_index() {
        return start_index;
    }

    public int getEnd_index() {
        return end_index;
    }

    @Override
    public String toString() {
        return MESSAGE_INIT
                + "Token: " + this.token + "\n"
                + "Start Index: " + this.start_index + "\n"
                + "End Index: " + this.end_index + "\n";
    }

//    @Override
//    public int compareTo(Match o) {
//        return this.rank - o.rank;
//    }
}
