package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

import static filesprocessing.FileProcessingHelper.toBytes;

/**
 * This class represents a custom filter
 */

public class GreaterThanFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public GreaterThanFilter() {
    }

    /**
     * This method filters files in an array so there'll be only files under a certain size
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        double bound = toBytes(Double.valueOf(filter[1]));
        for (File file : filterFiles) {
            if (file.length() > bound) {
                returnedList.add(file);
            }
        }
        return returnedList;
    }
}
