package com.janoschek.lottery_calculator.services.history;

public class ImportUnsuccessfulException extends RuntimeException {

    private static final String IMPORT_UNSUCCESSFUL_MSG = "Import unsuccessful";

    public ImportUnsuccessfulException() {
        super(IMPORT_UNSUCCESSFUL_MSG);
    }

    public ImportUnsuccessfulException(String message) {
        super(IMPORT_UNSUCCESSFUL_MSG + " Cause: " + message);
    }

    public ImportUnsuccessfulException(Throwable cause) {
        super(IMPORT_UNSUCCESSFUL_MSG, cause);
    }

    public ImportUnsuccessfulException(String message, Throwable cause) {
        super(IMPORT_UNSUCCESSFUL_MSG + " Cause: " + message, cause);
    }
}
