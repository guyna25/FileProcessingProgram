package filesprocessing;

import filesprocessing.commandFileHandling.CommandFileReader;
import filesprocessing.commandFileHandling.Section;
import filesprocessing.commandFileHandling.SectionFactory;
import filesprocessing.fileSorting.ComparatorFactory;
import filesprocessing.fileSorting.FileMergeSort;
import filesprocessing.sourcedirHandling.FileArrayFactory;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a program that can process a commandFile and a Sourcedirectory and act according
 * to PDR
 *
 * @author guyna25
 */

public class DirectoryProcessor {

    /**
     * The prefix for type 2 printed errors
     */

    private static final String ERROR_PREFIX = "ERROR: ";

    /**
     * The number of arguments this program receives
     */

    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Error message to be thrown if the amount of arguments doesn't match PDR
     */

    private static String ARGUMENT_OVERLOAD_ERROR_MESSAGE = "ERROR: exactly two arguments should be entered";

    /**
     * This method runs the fileProcessor
     *
     * @param args the arguments of the program
     */

    public static void main(String[] args) {

        if (args.length != NUMBER_OF_ARGUMENTS) {
            System.err.println(ARGUMENT_OVERLOAD_ERROR_MESSAGE);
            return;
        }
        try {
            runFileProcessor(args[0], args[1]);
        } catch (Exception caughtException) {
            System.err.println(ERROR_PREFIX + caughtException.getMessage());
        }


    }

    /**
     * This method class and runs all relevant program
     *
     * @param sourceDirPath   the path to sourceDir
     * @param commandFilePath the path to commandFile
     */

    private static void runFileProcessor(String sourceDirPath, String commandFilePath) throws Exception {
        ArrayList<Section> sectionArrayList = new SectionFactory().createSectionList
                (new CommandFileReader(commandFilePath).processFile());
        if (sectionArrayList.size() == 0) { ///commandfile is empty
            return;
        }
        ArrayList<File> fileArrayList = new FileArrayFactory(sourceDirPath).createFileArray();
        for (Section section : sectionArrayList) {
            ArrayList<File> tempFileist = new FileFilter().filterFiles(fileArrayList,
                    section.getFilter(), section.getNegateFilter());
            tempFileist = new FileMergeSort().mergeSortFiles(tempFileist,
                    new ComparatorFactory().createComparator(section.getOrder()),
                    section.getReverse());
            section.printWarnings();
            for (File file : tempFileist) {
                System.out.println(file.getName());
            }
        }
    }


}
