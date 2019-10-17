package seedu.address.model.password.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.analysis.UniqueAnalysisObject;

public class UniqueAnalyser implements Analyser{
    private static final String DESC_NOT_UNIQUE = "not unique";
    private static final String DESC_UNIQUE = "unique";
    public final String MESSAGE_HEADER = "Analysing passwords to check unique: \n";

    ArrayList<UniqueAnalysisObject> analysisObjects;

    @Override
    public void analyse(List<Password> accountList) {
        HashMap<String, ArrayList<Password>> passwordToAccounts = new HashMap<>();
        ArrayList<UniqueAnalysisObject> analysisObjects = new ArrayList<>();
        for (Password acc : accountList) {
            String password = acc.getPasswordValue().value;
            if (passwordToAccounts.containsKey(password)) {
                ArrayList<Password> arrList = passwordToAccounts.get(password);
                arrList.add(acc);
            } else {
                passwordToAccounts.put(password, new ArrayList<>());
                passwordToAccounts.get(password).add(acc);
            }
        }

        for (Password acc : accountList) {
            String password = acc.getPasswordValue().value;
            ArrayList<Password> arrList = passwordToAccounts.get(password);
            if (arrList.size() > 1) {
                analysisObjects.add(new UniqueAnalysisObject(acc, DESC_NOT_UNIQUE, arrList));
            } else {
                analysisObjects.add(new UniqueAnalysisObject(acc, DESC_UNIQUE, arrList));
            }
        }

        this.analysisObjects = analysisObjects;
    }

    @Override
    public String outputSummaryReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_HEADER);
        reportBuilder.append(MESSAGE_COLUMNS);
        for (UniqueAnalysisObject o : analysisObjects) {
            reportBuilder.append(o);
        }
        return reportBuilder.toString();
    }

    @Override
    public String outputDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append(MESSAGE_INIT);
        report.append(MESSAGE_HEADER);
        for (UniqueAnalysisObject o : analysisObjects) {
            report.append(o.getGreaterDetail());
        }
        return report.toString();
    }

}
