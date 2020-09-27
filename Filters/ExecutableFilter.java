package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a custom filter
 */

public class ExecutableFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public ExecutableFilter() {
    }

    /**
     * This method filters files in an array so that there'll only be files which can be executed
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        boolean executable = filter[1].equals("YES");
        for (File file : filterFiles) {
            if (executable) {
                if (file.canExecute()) {
                    returnedList.add(file);
                }
            } else {
                if (!file.canExecute()) {
                    returnedList.add(file);
                }
            }
        }
        return returnedList;
    }
}