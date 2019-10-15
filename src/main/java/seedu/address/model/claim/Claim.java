package seedu.address.model.claim;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commonvariables.Date;
import seedu.address.model.commonvariables.Name;
import seedu.address.model.commonvariables.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represent a Claim in the Financial Planner
 */
public abstract class Claim {

    // Identity fields
    private final Description description;
    private final Amount amount;
    private final Name name;
    private final Phone phone;
    private final Status status;

    // Data fields
    private final Date date;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Claim(Description description, Amount amount, Date date,
                 Name name, Phone phone, Set<Tag> tags, Status status) {
        requireAllNonNull(description, amount, name, phone, tags);
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.name = name;
        this.phone = phone;
        this.tags.addAll(tags);
        this.status = status;
    }

    public Description getDescription() {
        return description;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * This defines a weaker notion of equality between two claims.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean canChangeStatus() {
        return false;
    }

    /**
     * Returns true if both claims of the same description has at least one other identify field that is the same.
     */
    public boolean isSameClaim(Claim otherClaim) {
        if (otherClaim == this) {
            return true;
        }

        return otherClaim != null
                && otherClaim.getDescription().equals(getDescription())
                && (otherClaim.getName().equals(getName())
                        || otherClaim.getPhone().equals(getPhone())
                        || otherClaim.getAmount().equals(getAmount()));
    }

    /**
     * Returns true if both claims have same identity and data fields.
     * This defines a stronger notion of equality between two claims.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Claim)) {
            return false;
        }

        Claim otherClaim = (Claim) obj;
        return otherClaim.getDescription().equals(getDescription())
                && otherClaim.getName().equals(getName())
                && otherClaim.getDate().equals(getDate())
                && otherClaim.getPhone().equals(getPhone())
                && otherClaim.getAmount().equals(getAmount())
                && otherClaim.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Name: ")
                .append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
