package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

public interface BasicFilter {

    /**
     * This method is used to filter files according to a certain criteria
     *
     * @param filterFiles the files to be filtered
     * @param filter      ilter parameters
     * @return A filtered array
     */

    public ArrayList<File> filter(ArrayList<File> filterFiles, String[] filter);
}
