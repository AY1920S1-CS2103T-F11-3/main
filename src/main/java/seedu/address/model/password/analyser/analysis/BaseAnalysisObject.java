package seedu.address.model.password.analyser.analysis;

import java.util.Objects;

import seedu.address.model.password.Password;

public abstract class BaseAnalysisObject implements AnalysisObject {
    protected Password password;
    protected String description;

    public BaseAnalysisObject(Password password, String description) {
        this.password = password;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getGreaterDetail();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseAnalysisObject that = (BaseAnalysisObject) o;
        return password.equals(that.password);
    }

    @Override
    public String toString() {
        return this.password.getDescription()  + " : " + this.password.getUsername() + " : " + this.password.getPasswordValue() + " : " + getDescription() + "\n";
    }

}
