package filesprocessing.fileSorting;

import java.io.File;
import java.util.Comparator;

/**
 * This class represents an order of files by absolute path
 *
 * @author guyna25
 */

class AbsCompare implements Comparator<File> {

    /**
     * Constructor for this class
     */

    public AbsCompare() {

    }

    /**
     * This method compares between two files by absolute path
     *
     * @param firstFile  first file to be compared
     * @param secondFile second file to be compared
     * @return negative int if first file precedes the other, zero if they're equal and positive if the
     * first file comes after the second file
     */


    public int compare(File firstFile, File secondFile) {
        return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
    }
}
