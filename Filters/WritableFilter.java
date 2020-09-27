package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a custom filter
 */

public class WritableFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public WritableFilter() {
    }

    /**
     * This method filters files in an array so that there'll only be files which can be written to
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        boolean writable = filter[1].equals("YES");
        for (File file : filterFiles) {
            if (writable) {
                if (file.canWrite()) {
                    returnedList.add(file);
                }
            } else {
                if (!file.canWrite()) {
                    returnedList.add(file);
                }
            }
        }
        return returnedList;
    }
}