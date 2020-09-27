package filesprocessing;

/**
 * Helper class for filesprocessing excercise
 */

public class FileProcessingHelper {

    /**
     * The ratio between kilobytes and bytes
     */

    private static double KBYTES_TO_BYTES_RATE = 1024;

    /**
     * constructor for this class
     */

    public FileProcessingHelper() {
    }

    /**
     * This method converts kbytes into bytes
     *
     * @param lengthInKbytes the number to be converted
     * @return the converted number
     */

    public static double toBytes(double lengthInKbytes) {
        return lengthInKbytes * KBYTES_TO_BYTES_RATE;
    }
}
