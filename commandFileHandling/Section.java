package filesprocessing.commandFileHandling;

import java.util.ArrayList;

/**
 * This class represents a section in a commandFile
 *
 * @author guyna25
 */

public class Section {

    /**
     * Default filter when constructed
     */

    private String[] DEFAULT_FILTER = {"all"};

    /**
     * Default order when constructed
     */

    private String DEFAULT_ORDER = "abs";

    /**
     * The filter of this section
     */

    private String[] filter;

    /**
     * The order of this section
     */

    private String order;

    /**
     * If order in this section should be reversed
     */

    private boolean reverse = false;

    /**
     * If the filter in this section should be negated
     */

    private boolean negateFilter = false;

    /**
     * the warnings of this section
     */

    private ArrayList<String> warnings = new ArrayList<>();

    /**
     * First constructor for this class
     */

    public Section() {
        filter = DEFAULT_FILTER;
        order = DEFAULT_ORDER;
    }

    /**
     * @return the filter of this section
     */

    public String[] getFilter() {
        return filter;
    }

    /**
     * Getter method for reverse
     *
     * @return boolean value reverse
     */

    public boolean getReverse() {
        return reverse;
    }

    /**
     * @return the order of this section
     */

    public String getOrder() {
        return order;
    }

    /**
     * @return returns true if this section's filter should be negated
     */

    public boolean getNegateFilter() {
        return negateFilter;
    }

    /**
     * Setter method to change filter
     *
     * @param newFilter the new filter
     */

    public void setFilter(String[] newFilter) {
        filter = newFilter;
    }

    /**
     * Setter method to change order
     *
     * @param newOrder the new order
     */

    public void setOrder(String newOrder) {
        order = newOrder;
    }

    /**
     * Setter method for reverse
     *
     * @param newValue the boolean value reverse would be set to
     */

    public void setReverse(boolean newValue) {
        reverse = newValue;
    }

    /**
     * Setter method for negateFilter
     *
     * @param newValue the boolean value negateFilter would be set to
     */

    public void setNegateFilter(boolean newValue) {
        negateFilter = newValue;
    }

    /**
     * This method adds a warning to this section
     *
     * @param addedWarning the warning to be added
     */

    public void addWarning(String addedWarning) {
        warnings.add(addedWarning);
    }

    /**
     * This method prints all the warnings in this section
     */

    public void printWarnings() {
        for (String warning : warnings) {
            System.err.println(warning);
        }
    }
}
