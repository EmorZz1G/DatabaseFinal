package com.emor.dbfinal.exception;

public class ExerciseException extends Exception  {
    public ExerciseException() {
        super();
    }

    public ExerciseException(String message) {
        super(message);
    }

    public ExerciseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExerciseException(Throwable cause) {
        super(cause);
    }

    protected ExerciseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
