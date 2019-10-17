package seedu.address.model.password.analyser.analysis;

import java.util.ArrayList;

import seedu.address.model.password.Password;

public class UniqueAnalysisObject extends BaseAnalysisObject {
    ArrayList<Password> accountsSamePassword;

    public UniqueAnalysisObject(Password password, String description, ArrayList<Password> accountsSamePassword) {
        super(password, description);
        this.accountsSamePassword = accountsSamePassword;
    }

    @Override
    public String getGreaterDetail() {
        StringBuilder report = new StringBuilder("Result : " + description + "\n");
        if (accountsSamePassword.size() == 1) {
            return report.append("No accounts with similar passwords were found\n").toString();
        }
        report.append("The following accounts share the same password: \n");
        for (Password acc : accountsSamePassword) {
            report.append(acc + "\n"); //TODO implement AccountMatches
        }
        return report.toString();
    }
}
