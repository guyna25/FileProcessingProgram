package filesprocessing;

public class MissingFilterException extends Exception {

    private final String MISSING_FILTER_MESSAGE = "A FILTER is missing";

    public MissingFilterException() {
    }


    @Override
    public String getMessage() {
        return MISSING_FILTER_MESSAGE;
    }
}
