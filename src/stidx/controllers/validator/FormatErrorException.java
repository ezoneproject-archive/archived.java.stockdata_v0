package stidx.controllers.validator;


public class FormatErrorException extends Exception {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 5774807453233085615L;

    public FormatErrorException(String message) {
        super(message);
    }
}
