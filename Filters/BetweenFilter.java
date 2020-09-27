package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

import static filesprocessing.FileProcessingHelper.toBytes;

/**
 * This class represents a custom filter
 */

public class BetweenFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public BetweenFilter() {
    }

    /**
     * This method filters files so they'ed be in a range
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter parameters
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        double lowerBound = toBytes(Double.valueOf(filter[1]));
        double upperBound = toBytes(Double.valueOf(filter[2]));
        for (File file : filterFiles) {
            if (file.length() >= lowerBound && (file.length() <= upperBound)) {
                returnedList.add(file);
            }
        }
        return returnedList;
    }

}
