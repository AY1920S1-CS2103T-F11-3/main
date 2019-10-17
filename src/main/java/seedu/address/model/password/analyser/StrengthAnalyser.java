package seedu.address.model.password.analyser;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.analysis.AnalysisObject;
import seedu.address.model.password.analyser.analysis.StrengthAnalysisObject;

public class StrengthAnalyser implements Analyser{
    private static final String DESC_WEAK = "weak";
    private static final String DESC_MODERATE = "moderate";
    private static final String DESC_STRONG = "strong";
    public final String MESSAGE_HEADER = "Analysing passwords for strength: \n";

    ArrayList<StrengthAnalysisObject> analysisObjects;

    @Override
    public void analyse(List<Password> accountList) {
        ArrayList<StrengthAnalysisObject> analysisObjects = new ArrayList<>();
        for (Password p : accountList) {
            StrengthAnalysisObject o = calculateStrength(p);
            analysisObjects.add(o);
        }
        this.analysisObjects = analysisObjects;
    }

    public static StrengthAnalysisObject calculateStrength(Password passwordObject) { //TODO reccomend();
        String password = passwordObject.getPasswordValue().value;
        int passwordScore = 0;
        boolean hasMinimumLength = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNum = false;
        boolean hasSpecial = false;

        if (password.length() >= 8) {
            passwordScore += 1;
            hasMinimumLength = true;
        }
        //if it contains one digit
        if (password.matches("(?=.*[0-9]).*")) {
            passwordScore += 1;
            hasNum = true;
        }
        //if it contains one lower case letter
        if (password.matches("(?=.*[a-z]).*")) {
            passwordScore += 1;
            hasLower = true;
        }
        //if it contains one upper case letter
        if (password.matches("(?=.*[A-Z]).*")) {
            passwordScore += 1;
            hasUpper = true;
        }
        //if it contains one special character
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            passwordScore += 1;
            hasSpecial = true;
        }
        return generateAnalysisObject(passwordObject, passwordScore, hasMinimumLength, hasLower, hasUpper, hasNum, hasSpecial);
    }

    private static StrengthAnalysisObject generateAnalysisObject(Password passwordObject, int passwordScore, boolean hasMinimumLength, boolean hasLower, boolean hasUpper, boolean hasNum, boolean hasSpecial) {
        if (passwordScore <= 2) {
            return new StrengthAnalysisObject(passwordObject, DESC_WEAK, hasMinimumLength, hasLower, hasUpper, hasNum, hasSpecial);
        } else if (passwordScore <=4) {
            return new StrengthAnalysisObject(passwordObject, DESC_MODERATE, hasMinimumLength, hasLower, hasUpper, hasNum, hasSpecial);
        } else {
            return new StrengthAnalysisObject(passwordObject, DESC_STRONG, hasMinimumLength, hasLower, hasUpper, hasNum, hasSpecial);
        }
    }

    @Override
    public String outputSummaryReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_HEADER);
        reportBuilder.append(MESSAGE_COLUMNS);
        for (StrengthAnalysisObject o : analysisObjects) {
            reportBuilder.append(o);
        }
        return reportBuilder.toString();
    }

    @Override
    public String outputDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append(MESSAGE_INIT);
        report.append(MESSAGE_HEADER);
        for (StrengthAnalysisObject o : analysisObjects) {
            report.append(o.getGreaterDetail());
        }
        return report.toString();
    }
}
