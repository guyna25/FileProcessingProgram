package filesprocessing;

import filesprocessing.Filters.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class filters files
 *
 * @author guyna25
 */

public class FileFilter {

    /**
     * The name of the filter greater than
     */

    private final String GREATER_THAN = "greater_than";

    /**
     * The name of the filter between
     */

    private final String BETWEEN = "between";

    /**
     * The name of the filter smaller than
     */

    private final String SMALLER_THAN = "smaller_than";

    /**
     * The name of the filter file
     */

    private final String FILE = "file";

    /**
     * The name of the filter contains
     */

    private final String CONTAINS = "contains";

    /**
     * The name of the filter prefix
     */

    private final String PREFIX = "prefix";

    /**
     * The name of the filter suffix
     */

    private final String SUFFIX = "suffix";

    /**
     * The name of the filter writable
     */

    private final String WRITABLE = "writable";

    /**
     * The name of the filter executable
     */

    private final String EXECUTABLE = "executable";

    /**
     * The name of the filter hidden
     */

    private final String HIDDEN = "hidden";

    /**
     * The name of the filter all
     */

    private final String ALL = "all";

    /**
     * A map of all legal filters
     */

    private HashMap<String, BasicFilter> legalFilters = new HashMap<>();

    /**
     * Constructor for this class
     */

    public FileFilter() {
        legalFilters.put(GREATER_THAN, new GreaterThanFilter());
        legalFilters.put(BETWEEN, new BetweenFilter());
        legalFilters.put(SMALLER_THAN, new SmallerThanFilter());
        legalFilters.put(FILE, new MatchNameFilter());
        legalFilters.put(CONTAINS, new ContainNameFilter());
        legalFilters.put(PREFIX, new PrefixNameFilter());
        legalFilters.put(SUFFIX, new SuffixNameFilter());
        legalFilters.put(WRITABLE, new WritableFilter());
        legalFilters.put(EXECUTABLE, new ExecutableFilter());
        legalFilters.put(HIDDEN, new IsHiddenFilter());
        legalFilters.put(ALL, new AllFilter());
    }

    /**
     * This method filters the files in accordance of the filter given to it
     *
     * @param filterFiles  The files to be filtered
     * @param filter       the filter to be used
     * @param negateFilter should the  filter be negated
     * @return An array of the files after filtring
     */

    public ArrayList<File> filterFiles(ArrayList<File> filterFiles, String[] filter, boolean negateFilter) {
        ArrayList<File> returnedFilesList = new ArrayList<>();
        if (legalFilters.containsKey(filter[0])) {
            returnedFilesList = legalFilters.get((filter[0])).filter(filterFiles, filter);
        }
        return negateFilter ? removeFile(filterFiles, returnedFilesList) : returnedFilesList;
    }

    /**
     * Helper method for filtering which removes certain files from a file array
     *
     * @param filteredFiles the files that are being filtered
     * @param invalidFiles  the files to be removed from the array
     * @return Array without the files input in invalidFiles
     */

    private ArrayList<File> removeFile(ArrayList<File> filteredFiles, ArrayList<File> invalidFiles) {
        ArrayList<File> returnedList = new ArrayList<>();
        for (File file : filteredFiles) {
            if (invalidFiles.contains(file)) {
                continue;
            }
            returnedList.add(file);
        }
        return returnedList;
    }
}
