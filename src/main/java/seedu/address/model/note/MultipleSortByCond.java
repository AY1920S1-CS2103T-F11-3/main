package seedu.address.model.note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Note's Sorting condition.
 */
public class MultipleSortByCond {

    public static final String MESSAGE_CONSTRAINTS = "Sort condition must have at least one " + SortByCond.DATEADDED
            + ", or "
            + SortByCond.DATEMODIFIED + " or " + SortByCond.NUMOFACCESS + " separated by a whitespace each";

    public final String[] multipleSortByCond;
    public final Comparator<Note> multipleSortComparator;

    /**
     * Constructs an {@code SortByCond} field with date modified as the default condition.
     */
    public MultipleSortByCond(String[] sortConditions) {
        this.multipleSortByCond = sortConditions;
        this.multipleSortComparator = buildComparator(sortConditions);
    }

    /**
     * Builds a comparator based on the sort conditions provided.
     * @param sortConditions decreasing precedence of the sorting condition as the index number
     * of the sort condition increases in the String array.
     * @return
     */
    public Comparator<Note> buildComparator(String[] sortConditions) {
        ArrayList<Comparator<Note>> noteComparators = new ArrayList<>();
        for (String sortCond : sortConditions) {
            SortByCond currentCond = new SortByCond(sortCond);
            Comparator<Note> currentComparator = currentCond.getSortComparator();
            noteComparators.add(currentComparator);
        }
        return new MultipleComparator(noteComparators);

    }

    public Comparator<Note> getSortComparator() {
        return multipleSortComparator;
    }


    /**
     * Gets the Comparator object based on the sorting condition.
     *
     * @return Comparator object.
     */
    public Comparator<Note> getMultipleSortComparator() {
        return multipleSortComparator;
    }

    /**
     * Returns true if given string conditions has no duplicates and are correct conditions individually.
     */
    public static boolean isValidSortByCond(String[] sortConditions) {
        //true by default
        boolean isDuplicate = false;
        boolean isCorrectCond = true;
        for (String cond : sortConditions) {
            String currentCond = cond.toLowerCase();
            Set<String> checkDuplicate = new HashSet<>();
            if (checkDuplicate.contains(currentCond)) {
                isDuplicate = true;
            } else {
                checkDuplicate.add(currentCond);
            }
            if (!(currentCond.equals(SortByCond.DATEMODIFIED) || currentCond.equals(SortByCond.DATEADDED)
                    || currentCond.equals(SortByCond.NUMOFACCESS))) {
                isCorrectCond = false;
            }
        }
        return !isDuplicate && isCorrectCond;
    }

    @Override
    public String toString() {
        return multipleSortByCond.toString();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MultipleSortByCond // instanceof handles nulls
                && multipleSortByCond.equals(((MultipleSortByCond) other).multipleSortByCond)); // state check
    }

    @Override
    public int hashCode() {
        return multipleSortByCond.hashCode();
    }

    /**
     * Comparator class that compares notes based on its NumOfAccess attribute.
     */
    class MultipleComparator implements Comparator<Note> {
        private final List<Comparator<Note>> comparators;

        private MultipleComparator(List<Comparator<Note>> comparators) {
            this.comparators = comparators;
        }

        /**
         * https://stackoverflow.com/questions/4258700/collections-sort-with-multiple-fields
         * @param a
         * @param b
         * @return
         */
        @Override
        public int compare(Note a, Note b) {
            for (Comparator<Note> comparator : comparators) {
                int result = comparator.compare(a, b);
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }


    }
}
