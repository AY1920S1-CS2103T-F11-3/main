package seedu.address.model.password.analyser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.analysis.SimilarityAnalysisObject;

public class SimilarityAnalyser implements Analyser {

    private static final String MESSAGE_HEADER = "Analyzing password for similarity: \n";
    ArrayList<SimilarityAnalysisObject> analysisObjects;

    @Override
    public void analyse(List<Password> passwordList) {
        ArrayList<SimilarityAnalysisObject> analysisObjects = new ArrayList<>();
        for (Password acc : passwordList) {
            ArrayList<Password> similars = findSimilarPasswords(acc, passwordList);
            if (!similars.isEmpty()) {
                analysisObjects.add(new SimilarityAnalysisObject(acc, DESC_FAIL, similars));
            } else {
                analysisObjects.add(new SimilarityAnalysisObject(acc, DESC_PASS, similars));
            }
        }
        this.analysisObjects = analysisObjects;
    }

    private ArrayList<Password> findSimilarPasswords(Password toCheck, List<Password> passwordList) {
        String s1 = toCheck.getPasswordValue().value;
        ArrayList<Password> similars = new ArrayList<>();
        for (Password acc : passwordList) {
            if (toCheck == acc) {
                continue;
            }
            String s2 = acc.getPasswordValue().value;
            double score = score(s1, s2);
            if(score >= 0.75) {
                similars.add(acc);
            }
        }
        return similars;
    }

    /**
     * Calculates the similarity score of objects, where 0.0 implies absolutely no similarity
     * and 1.0 implies absolute similarity.
     *
     * @param first The first string to compare.
     * @param second The second string to compare.
     * @return A number between 0.0 and 1.0.
     * @throws NullPointerException if one or both of the strings are null
     */
    public double score(String first, String second) {
        int maxLength = Math.max(first.length(), second.length());
        //Can't divide by 0
        if (maxLength == 0) return 1.0d;
        return ((double) (maxLength - computeEditDistance(first, second))) / (double) maxLength;
    }

    protected int computeEditDistance(String first, String second) {
        first = first.toLowerCase();
        second = second.toLowerCase();

        int[] costs = new int[second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            int previousValue = i;
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                }
                else if (j > 0) {
                    int useValue = costs[j - 1];
                    if (first.charAt(i - 1) != second.charAt(j - 1)) {
                        useValue = Math.min(Math.min(useValue, previousValue), costs[j]) + 1;
                    }
                    costs[j - 1] = previousValue;
                    previousValue = useValue;

                }
            }
            if (i > 0) {
                costs[second.length()] = previousValue;
            }
        }
        return costs[second.length()];
    }

    @Override
    public String outputSummaryReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_HEADER);
        reportBuilder.append(MESSAGE_COLUMNS);
        for (SimilarityAnalysisObject o : analysisObjects) {
            reportBuilder.append(o);
        }
        return reportBuilder.toString();
    }

    @Override
    public String outputDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append(MESSAGE_INIT);
        report.append(MESSAGE_HEADER);
        for (SimilarityAnalysisObject o : analysisObjects) {
            report.append(o.getGreaterDetail());
        }
        return report.toString();
    }
}
