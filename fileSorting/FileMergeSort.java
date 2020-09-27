package filesprocessing.fileSorting;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a sorting of files with merge sort
 *
 * @author guyna25
 */

public class FileMergeSort {

    /**
     * The constructor for this class
     */

    public FileMergeSort() {
    }

    /**
     * This method sorts the files with input comparator
     *
     * @param sortedList     the list to be sortd
     * @param usedComparison the comparator to be used
     * @param inverse        should the sorted list be revered
     * @return a sorted ArrayList
     */

    public ArrayList<File> mergeSortFiles(ArrayList<File> sortedList, Comparator<File> usedComparison,
                                          boolean inverse) {
        ArrayList<File> usedArray = new ArrayList<>(sortedList);
        mergeSort(usedArray, usedComparison);
        if (inverse) {
            Collections.reverse(usedArray);
        }
        return usedArray;
    }

    /**
     * This is a helper method for mergeSortFiles which uses mergeSort on the given array
     *
     * @param listToSort     The list to be sorted
     * @param fileComparator the comparator to be used
     */

    private void mergeSort(ArrayList<File> listToSort, Comparator<File> fileComparator) {
        int size = listToSort.size();
        if (size < 2) {
            return;
        }
        int half = size / 2;
        ArrayList<File> firstList = new ArrayList<File>(listToSort.subList(0, half));
        ArrayList<File> secondList = new ArrayList<File>(listToSort.subList(half, size));

        mergeSort(firstList, fileComparator);
        mergeSort(secondList, fileComparator);

        merge(firstList, secondList, listToSort, fileComparator);
    }

    /**
     * Helper method for mergeSort which merges two arrays while maintaining the order between them
     *
     * @param firstList      first array to be merged
     * @param secondList     second array to be merged
     * @param wholeList      The list the arrays should be merged into
     * @param usedComparison THe comparator to be used to compare between files
     * @param <File>         A sorted merged array
     */

    private static <File> void merge(ArrayList<File> firstList, ArrayList<File> secondList,
                                     ArrayList<File> wholeList, Comparator<File> usedComparison) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < firstList.size() && j < secondList.size()) {
            if (usedComparison.compare(firstList.get(i), secondList.get(j)) < 0) {
                wholeList.set(k++, firstList.get(i++));
            } else {
                wholeList.set(k++, secondList.get(j++));
            }
        }
        while (i < firstList.size()) {
            wholeList.set(k++, firstList.get(i++));
        }
        while (j < secondList.size()) {
            wholeList.set(k++, secondList.get(j++));
        }
    }


}
