package filesprocessing.fileSorting;

import java.io.File;
import java.util.Comparator;

/**
 * This class represents an order of files by size
 *
 * @author guyna25
 */

class SizeCompare implements Comparator<File> {

    /**
     * Constructor for this class
     */

    public SizeCompare() {
    }

    /**
     * This method compares between two files by size
     *
     * @param firstFile  first file to be compared
     * @param secondFile second file to be compared
     * @return negative int if first file precedes the other, zero if they're equal and positive if the
     * first file comes after the second file
     */

    public int compare(File firstFile, File secondFile) {
        if (firstFile.length() == secondFile.length()) {
            return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
        } else {
            return Double.compare(firstFile.length(), secondFile.length());
        }
    }
}
