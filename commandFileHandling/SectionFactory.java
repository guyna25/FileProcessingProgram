package filesprocessing.commandFileHandling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a section factory
 *
 * @author guyna25
 */

public class SectionFactory {

    /**
     * Error message when first line isn't FILTER
     */

    private final String ILLEGAL_PARAMETER_ERROR_MESSAGE = "Warning in line ";

    /**
     * The first component of each subsection
     */

    private final String FILTER_COMPONENT = "FILTER";

    /**
     * The valid filters which include a double
     */

    private final String[] VALID_DOUBLE_FILTERS = {"greater_than", "between", "smaller_than"};

    /**
     * The valid filters which use a string
     */

    private final String[] VALID_STRING_FILTERS = {"file", "contains", "prefix", "suffix"};

    /**
     * The vaild filters which use a file's attribute
     */

    private final String[] VALID_ATTRIBUTE_FILTERS = {"writable", "executable", "hidden"};

    /**
     * Valid filters which do not use a specific quality
     */

    private final String[] VALID_GENERAL_FILTERS = {"all"};

    /**
     * The valid values for filters which use an attribute
     */

    private final String[] VALID_ATTRIBUTE_VALUE = {"YES", "NO"};

    /**
     * The valid values for ordering
     */

    private final String[] VALID_ORDER = {"abs", "type", "size"};

    /**
     * The valid values for filters which have a suffix condition
     */

    private final String[] VALID_FILTER_SUFFIX = {"NOT"};

    /**
     * The valid values for orders which have a suffix condition
     */

    private final String[] VALID_ORDER_SUFFIX = {"REVERSE"};

    /**
     * The seperator used in commandline
     */

    private final String SEPARATOR = "#";

    /**
     * Constructor for this class
     */

    public SectionFactory() {
    }

    /**
     * This function process an array of strings into usable sections
     *
     * @param rawSectionArray an array of strings
     * @return an arrayList of sections, array will be not initialized if an error occurred
     */

    public ArrayList<Section> createSectionList(ArrayList<String> rawSectionArray) {
        ArrayList<Section> returnedSectionList = new ArrayList<>();
        for (int index = 0; index < rawSectionArray.size(); index++) {
            if (FILTER_COMPONENT.equals(rawSectionArray.get(index))) {
                if (rawSectionArray.size() > index + 3) { //if so, filter has to be either 3 or 4 gap
                    if (FILTER_COMPONENT.equals(rawSectionArray.get(index + 3))) {
                        returnedSectionList.add(createSection(rawSectionArray.subList(index
                                , index + 3), index + 1));
                    } else {
                        returnedSectionList.add(createSection(new ArrayList<>(rawSectionArray.subList(index
                                , index + 4)), index + 1));
                    }
                } else {
                    returnedSectionList.add(createSection(rawSectionArray.subList(index
                            , index + 3), index + 1));
                }
            }
        }
        return returnedSectionList;
    }

    /**
     * This method is a helper method for createSectionList, it processes a string and returns a section
     *
     * @param rawSection the string to be processed
     * @param startLine  the relative line in which the string starts
     * @return If the section matched PDR it returns a section, otherwise a not initialized section is
     * returned
     */

    private Section createSection(List<String> rawSection, int startLine) {
        Section returnedSection = new Section();
        String[] sectionFilter = rawSection.get(1).split(SEPARATOR); //Notice array out of bound error
        // cannot happen as section sizes were checked earlier
        if (checkFIlter(sectionFilter)) {
            if (Arrays.asList(VALID_FILTER_SUFFIX).contains(sectionFilter[sectionFilter.length - 1])) {
                returnedSection.setNegateFilter(true);
            }
            returnedSection.setFilter(sectionFilter);
        } else {
            returnedSection.addWarning(ILLEGAL_PARAMETER_ERROR_MESSAGE + String.valueOf(startLine + 1));
        }
        if (rawSection.size() == 4 && checkOrder(rawSection.get(3).split(SEPARATOR))) {
            String[] orderArray = rawSection.get(3).split(SEPARATOR);
            if (orderArray.length == 2) {
                returnedSection.setReverse(true);
            }
            returnedSection.setOrder(orderArray[0]);
        } else {
            if (rawSection.size() != 3) {
                returnedSection.addWarning(ILLEGAL_PARAMETER_ERROR_MESSAGE + String.valueOf(startLine + 3));
            }
        }
        return returnedSection;
    }

    /**
     * This method validates that an order matches PDR
     *
     * @param checkedOrder the order to be checked
     * @return true if order matches PDR, false otherwise
     */

    private boolean checkOrder(String[] checkedOrder) {
        if ((checkedOrder.length == 1 || checkedOrder.length == 2) &&
                (Arrays.asList(VALID_ORDER).contains(checkedOrder[0]))) {
            if (checkedOrder.length == 2) {
                return Arrays.asList(VALID_ORDER_SUFFIX).contains(checkedOrder[1]);
            }
            return true;
        }
        return false;
    }

    /**
     * This method checks if a filter matches PDR
     *
     * @param checkedFilter the filter to be checked
     * @return true if filter matched PDR, false otherwise
     */

    private boolean checkFIlter(String[] checkedFilter) {
        if (Arrays.asList(VALID_GENERAL_FILTERS).contains(checkedFilter[0])) {
            if (checkedFilter.length == 1) {
                return true;
            }
            if (checkedFilter.length == 2 && Arrays.asList(VALID_FILTER_SUFFIX).contains(checkedFilter[1])) {
                return true;
            }
        }
        if (Arrays.asList(VALID_DOUBLE_FILTERS).contains(checkedFilter[0])) {
            return checkDoubleFilter(checkedFilter);
        }

        if (Arrays.asList(VALID_STRING_FILTERS).contains(checkedFilter[0])) {
            if (checkedFilter.length == 2) {
                return true;
            }
            if (checkedFilter.length == 3) {
                return Arrays.asList(VALID_FILTER_SUFFIX).contains(checkedFilter[2]);
            }
        }

        if (Arrays.asList(VALID_ATTRIBUTE_FILTERS).contains(checkedFilter[0])) {
            return checkAttributeFilter(checkedFilter);
        }
        return false;
    }

    /**
     * Helper method for checkFilter which checks a specific type of filter
     *
     * @param attributeFilterArray the filter to be checked
     * @return true if filter matched PDR, false otherwise
     */

    private boolean checkAttributeFilter(String[] attributeFilterArray) {
        if (attributeFilterArray.length == 2) {
            return Arrays.asList(VALID_ATTRIBUTE_VALUE).contains(attributeFilterArray[1]);
        }
        if (attributeFilterArray.length == 3) {
            if (Arrays.asList(VALID_FILTER_SUFFIX).contains(attributeFilterArray[2])) {
                return Arrays.asList(VALID_ATTRIBUTE_VALUE).contains(attributeFilterArray[1]);
            }
        }
        return false;
    }

    /**
     * Helper method for checkFilter which checks a specific type of filter
     *
     * @param doubleFilterArray the filter to be checked
     * @return true if filter matched PDR, false otherwise
     */

    private boolean checkDoubleFilter(String[] doubleFilterArray) {
        try {
        if ("between".equals(doubleFilterArray[0])) {
                double lowerBound = Double.parseDouble(doubleFilterArray[1]);
                double upperBound = Double.parseDouble(doubleFilterArray[2]);
                if (doubleFilterArray.length == 4 || doubleFilterArray.length == 3) {
                    if (doubleFilterArray.length == 4 &&
                            !Arrays.asList(VALID_FILTER_SUFFIX).contains(doubleFilterArray[3])) {
                        return false;
                    }
                    return lowerBound>=0 && upperBound>=lowerBound;
                }
        } else {

                if ((doubleFilterArray.length != 2 && doubleFilterArray.length != 3) ||
                        Double.parseDouble(doubleFilterArray[1]) < 0) {
                    return false;
                }

                if (doubleFilterArray.length == 3) {
                    return Arrays.asList(VALID_FILTER_SUFFIX).contains(doubleFilterArray[2]);
                }
            }
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

}