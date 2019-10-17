package seedu.address.model.password.analyser;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.analysis.SequenceAnalysisObject;
import seedu.address.model.password.analyser.match.Match;

public class SequenceAnalyser implements Analyser {

    private static final String MESSAGE_HEADER = "Analysing passwords for common sequences :\n";

    private ArrayList<SequenceAnalysisObject> analysisObjects;

    @Override
    public void analyse(List<Password> passwordList) {
        ArrayList<SequenceAnalysisObject> analysisObjects = new ArrayList<>();
        for (Password acc : passwordList) {
            String password = acc.getPasswordValue().value;
            List<Match> matches = getAllSubseq(password);
            if (matches.isEmpty()) {
                analysisObjects.add(new SequenceAnalysisObject(acc, DESC_PASS, matches));
            } else {
                analysisObjects.add(new SequenceAnalysisObject(acc, DESC_FAIL, matches));
            }
        }
        this.analysisObjects = analysisObjects;
    }

    private List<Match> getAllSubseq(String password) {
        ArrayList<Match> matches = new ArrayList<>();
        getAllForwardSubseq(password, matches);
        getAllBackwardSubseq(password, matches);
        return matches;
    }

    private static void getAllForwardSubseq(String password, ArrayList<Match> matches) {
        char[] characters = password.toCharArray();
        if (password.length() <= 2) {
            return; //dont bother
        }
        int start = 0;
        while (start < characters.length - 1) { //while start not at the end
            StringBuilder seq = new StringBuilder();
            Character curr = characters[start];
            seq.append(curr);
            int end = start + 1;
            Character next = characters[end];
            while (next == curr + 1 && end <= characters.length -1 && inSameRange(curr, next)) {
                seq.append(next);
                end++;
                if (end == characters.length) break;
                curr = next;
                next = characters[end];
            }
            if (seq.length() >= 3) {
                matches.add(new Match(start, end, seq.toString(), -1));
            }
            start = end;
        }
        return;
    }

    private static void getAllBackwardSubseq(String password, ArrayList<Match> matches) {
        char[] characters = password.toCharArray();
        if (password.length() <= 2) {
            return; //dont bother
        }
        int start = 0;
        while (start < characters.length - 1) { //while start not at the end
            StringBuilder seq = new StringBuilder();
            Character curr = characters[start];
            seq.append(curr);
            int end = start + 1;
            Character next = characters[end];
            while (next == curr - 1 && end <= characters.length -1 && inSameRange(curr, next)) {
                seq.append(next);
                end++;
                if (end == characters.length) break;
                curr = next;
                next = characters[end];
            }
            if (seq.length() >= 3) {
                matches.add(new Match(start, end, seq.toString(), -1));
            }
            start = end;
        }
        return;
    }

    private static boolean inSameRange(Character curr, Character next) {
        if (curr >= 65 && curr <= 90) //ALPHA_UPPER
            return (next >= 65 && next <= 90);
        else if (curr >= 97 && curr <= 122) { //ALPHA_LOWER:
            return (next >= 97 && next <= 122);
        } else if (curr >= 48 && curr <= 57){ //NUMERICAL:
                return (next >= 48 && next <= 57);
        } else {
            return false;
        }
    }

    @Override
    public String outputSummaryReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_HEADER);
        reportBuilder.append(MESSAGE_COLUMNS);
        for (SequenceAnalysisObject o : analysisObjects) {
            reportBuilder.append(o);
        }
        return reportBuilder.toString();
    }

    @Override
    public String outputDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append(MESSAGE_INIT);
        report.append(MESSAGE_HEADER);
        for (SequenceAnalysisObject o : analysisObjects) {
            report.append(o.getGreaterDetail());
        }
        return report.toString();
    }
}
