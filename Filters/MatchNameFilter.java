package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a custom filter
 */

public class MatchNameFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public MatchNameFilter() {
    }

    /**
     * This method filters files in an array so there'll be only files that match a certain name
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        String fileName = filter[1];
        for (File file : filterFiles) {
            if (file.getName().equals(fileName)) {
                returnedList.add(file);
            }
        }
        return returnedList;
    }
}
