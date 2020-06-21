package com.emor.dbfinal.exception;

public class ExaminationException extends Exception  {
    public ExaminationException() {
        super();
    }

    public ExaminationException(String message) {
        super(message);
    }

    public ExaminationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExaminationException(Throwable cause) {
        super(cause);
    }

    protected ExaminationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
