package seedu.address.model.password.analyser.analysis;

import java.util.List;

import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.match.Match;

public class SequenceAnalysisObject extends BaseAnalysisObject {
    private List<Match> matches;

    public SequenceAnalysisObject(Password password, String description, List<Match> matches) {
        super(password, description);
        this.matches = matches;
    }

    @Override
    public String getGreaterDetail() {
        StringBuilder report = new StringBuilder("Result : " + description + "\n");
        if (matches.isEmpty()) {
            report.append("No passwords were found to have contained common sequences");
            return report.toString();
        }
        for (Match m : matches) {
            report.append(m);
        }
        return report.toString();
    }
}
