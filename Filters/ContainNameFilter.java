package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a custom filter
 */

public class ContainNameFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public ContainNameFilter() {
    }

    /**
     * This method filters files in an array so there'll be only files that their names contain a certain
     * string
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        String searchedValue = filter[1];
        for (File file : filterFiles) {
            if (file.getName().contains(searchedValue)) {
                returnedList.add(file);
            }
        }
        return returnedList;
    }
}
