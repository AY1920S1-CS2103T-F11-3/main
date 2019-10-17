package seedu.address.model.password.analyser.analysis;

import java.util.ArrayList;

import seedu.address.model.password.Password;

public class SimilarityAnalysisObject extends BaseAnalysisObject {

    private ArrayList<Password> similars;

    public SimilarityAnalysisObject(Password password, String description, ArrayList<Password> similars) {
        super(password, description);
        this.similars = similars;
    }

    @Override
    public String getGreaterDetail() {
        StringBuilder report = new StringBuilder("Result : " + description + "\n");
        if (similars.isEmpty()) {
            return report.append("No accounts with similar passwords were found\n").toString();
        }
        for (Password acc : similars) {
            report.append(acc + "\n"); //TODO implement AccountMatches
        }
        return report.toString();
    }
}
