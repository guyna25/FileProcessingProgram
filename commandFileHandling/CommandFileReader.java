package filesprocessing.commandFileHandling;

import filesprocessing.MissingFilterException;
import filesprocessing.MissingOrderException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads and parses a command file
 *
 * @author guyna25
 */

public class CommandFileReader {

    /**
     * The longest a section can be
     */

    private final int MAX_SECTION_LENGTH = 4;

    /**
     * The first component of each subsection
     */

    private final String FILTER_COMPONENT = "FILTER";

    /**
     * The second component of each subsection
     */

    private final String ORDER_COMPONENT = "ORDER";

    /**
     * The distance between a filter and an order according to pdr
     */

    private final int FILTER_ORDER_DISTANCE = 2;

    /**
     * The scanner used to read this file
     */

    private Scanner fileReader;

    /**
     * Constructor for this class
     *
     * @param filePath the path of the commandFile
     */

    public CommandFileReader(String filePath) throws Exception {
        fileReader = new Scanner(new File(filePath));
    }

    /**
     * Thie method processes the file given to it during construction
     *
     * @return An array of lines if no error arises, null otherwise
     */

    public ArrayList<String> processFile() throws Exception {
        ArrayList<String> returnedArray = siftRedundantComponents(parseToArray());
        checkSectionStructure(returnedArray);
        return returnedArray;
    }

    /**
     * This method parses the text into an array
     *
     * @return an array of text lines
     */

    private ArrayList<String> parseToArray() {
        ArrayList<String> lineArray = new ArrayList<>();
        while (fileReader.hasNext()) {
            lineArray.add(fileReader.nextLine());
        }
        return lineArray;
    }

    /**
     * This method replaces redundant components with their line number
     *
     * @param siftedArray the array to be sifted
     * @return a sifted array, with line numbers replacing indexes where there was an unecssary component
     */

    private ArrayList<String> siftRedundantComponents(ArrayList<String> siftedArray) {
        ArrayList<String> returnedArray = new ArrayList<>();
        if (siftedArray.size() == 0) {
            return returnedArray;
        }
        returnedArray.add(siftedArray.get(0));
        for (int index = 1; index < siftedArray.size(); index++) {
            if (FILTER_COMPONENT.equals(siftedArray.get(index)) &&
                    FILTER_COMPONENT.equals(siftedArray.get(index - 1))) {
                returnedArray.add(String.valueOf(index + 1));
                continue;
            }
            if (ORDER_COMPONENT.equals(siftedArray.get(index)) && (FILTER_COMPONENT.equals(returnedArray
                    .get(index - 1)) || ORDER_COMPONENT.equals(returnedArray.get(index - 1)))) {
                returnedArray.add(String.valueOf(index + 1));
                continue;
            }
            returnedArray.add(siftedArray.get(index));
        }
        return returnedArray;
    }

    /**
     * This method verifies that each section follows the structure described in the PDR
     *
     * @param checkedArray the section array
     * @return true if array is well structured, false otherwise
     */

    private void checkSectionStructure(ArrayList<String> checkedArray) throws Exception {
        int startIndex = 0;
        int endIndex;
        if (checkedArray.size() < 3 || !FILTER_COMPONENT.equals(checkedArray.get(0))) {
            throw new MissingFilterException();
        }
        if (!ORDER_COMPONENT.equals(checkedArray.get(startIndex + 2))) {
            throw new MissingOrderException();
        }
        for (int index = 1; index < checkedArray.size(); index++) {
            if (FILTER_COMPONENT.equals(checkedArray.get(index))) {
                endIndex = index;
                if ((endIndex - startIndex) > MAX_SECTION_LENGTH || startIndex + FILTER_ORDER_DISTANCE >
                        checkedArray.size()) {
                    //the gap between two components is too big or the last section is too short
                    throw new MissingFilterException();
                }
                if (!ORDER_COMPONENT.equals(checkedArray.get(endIndex + FILTER_ORDER_DISTANCE))) {
                    throw new MissingOrderException();
                }
                startIndex = endIndex;
            }
        }

    }
}
