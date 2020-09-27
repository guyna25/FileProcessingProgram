package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a custom filter
 */


public class IsHiddenFilter implements BasicFilter {

    /**
     * Constructor for this class
     */

    public IsHiddenFilter() {
    }

    /**
     * This method filters files in an array so that there'll only be files which are/aren't hidden
     *
     * @param filterFiles filteredFiles the files to be filtered
     * @param filter      filter patameteres
     * @return A filtered array
     */

    @Override
    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter) {
        ArrayList<File> returnedList = new ArrayList<>();
        boolean hidden = filter[1].equals("YES");
        for (File file : filterFiles) {
            if (hidden) {
                if (file.isHidden()) {
                    returnedList.add(file);
                }
            } else {
                if (!file.isHidden()) {
                    returnedList.add(file);
                }
            }
        }
        return returnedList;
    }
}
