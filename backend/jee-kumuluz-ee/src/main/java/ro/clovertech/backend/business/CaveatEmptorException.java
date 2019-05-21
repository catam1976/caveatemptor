package ro.clovertech.backend.business;

public class CaveatEmptorException extends Exception {

    public static final int INVALID_CATEGORY_NAME = 0;
    public static final int INVALID_CATEGORY_PARENT_ID = 1;

    private int errorCode;

    public CaveatEmptorException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CaveatEmptorException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CaveatEmptorException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
