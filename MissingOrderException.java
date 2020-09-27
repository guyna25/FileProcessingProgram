package filesprocessing;

public class MissingOrderException extends Exception {

    private final String MISSING_ORDER_MESSAGE = "An ORDER is missing";

    public MissingOrderException() {
    }


    @Override
    public String getMessage() {
        return MISSING_ORDER_MESSAGE;
    }
}
