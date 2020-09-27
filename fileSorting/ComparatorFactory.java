package filesprocessing.fileSorting;

import java.io.File;
import java.util.Comparator;


/**
 * This class is a factory for excercise-relevant comparators
 *
 * @author guyna25
 */

public class ComparatorFactory {

    /**
     * Constructor for this class
     */

    public ComparatorFactory() {
    }

    /**
     * This method creates a comparator in accordance of input
     *
     * @param comparatorType the type of comparator to be created
     * @return Relevant comparator if comparator matches Order's PDR, comparator by abs value otherwise
     */

    public Comparator<File> createComparator(String comparatorType) {
        switch (comparatorType) {
            case "type":
                return new TypeCompare();
            case "size":
                return new SizeCompare();
            default:
                return new AbsCompare();
        }
    }
}
