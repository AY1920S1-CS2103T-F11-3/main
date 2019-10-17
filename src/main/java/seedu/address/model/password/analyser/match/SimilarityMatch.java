package seedu.address.model.password.analyser.match;

import seedu.address.model.password.Password;

public class SimilarityMatch extends BaseMatch implements Comparable<SimilarityMatch> {
    private double similarity;
    private Password password;
    public SimilarityMatch(int start_index, int end_index, String token, Password password, double similarity) {
        super(start_index, end_index, token);
        this.password = password;
        this.similarity = similarity;
    }

    @Override
    public int compareTo(SimilarityMatch o) {
        return Double.compare(o.similarity, this.similarity);
    }

    @Override
    public String toString() {
        return super.toString() + "Type : Similarity Match\n"
                + "Account : " + this.password
                + "Similarity : " + this.similarity + "\n";
    }
}
