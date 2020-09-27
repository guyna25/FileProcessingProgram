package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a custom filter
 */


public class AllFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public AllFilter() {
    }

    /**
     * This method does not filter any files
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {

        return filterFiles;
    }
}
